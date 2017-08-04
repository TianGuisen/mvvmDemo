package tgs.com.mvvm.core.retrofit;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * Created by zhouqinglong on 2017/3/28 15:34.
 * 联网日志拦截器,会打印所有信息
 */

public class LoggerInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String HTTP_LOG_TAG = "retrofit";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // -------------------------------- request----------------------------------------
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        if (hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        Log.i(HTTP_LOG_TAG, requestStartMessage);

        if (hasRequestBody) {
            // Request body headers are only present when installed as a network interceptor. Force
            // them to be included (when available) so there values are known.
            if (requestBody.contentType() != null) {
                Log.i(HTTP_LOG_TAG, "Content-Type: " + requestBody.contentType());
            }
            if (requestBody.contentLength() != -1) {
                Log.i(HTTP_LOG_TAG, "Content-Length: " + requestBody.contentLength());
            }
        }

        Headers headers = request.headers();
        for (int i = 0, count = headers.size(); i < count; i++) {
            String name = headers.name(i);
            // Skip headers from the request body as they are explicitly logged above.
            if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                Log.i(HTTP_LOG_TAG, name + ": " + headers.value(i));
            }
        }
        Log.i(HTTP_LOG_TAG, "--> END " + request.method());

        if (bodyEncoded(request.headers())) {
            Log.i(HTTP_LOG_TAG, "--> END " + request.method() + " (encoded body omitted)");
        } else {
            if (hasRequestBody) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                Log.i(HTTP_LOG_TAG, buffer.readString(charset));
                Log.i(HTTP_LOG_TAG, "--> END " + request.method()
                        + " (" + requestBody.contentLength() + "-byte body)");
            }
        }

        //-------------------------reponse-------------------------------
        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        Log.i(HTTP_LOG_TAG, "<-- " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms)");

        Headers responseHeaders = response.headers();
        for (int i = 0, count = responseHeaders.size(); i < count; i++) {
            Log.i(HTTP_LOG_TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        if (bodyEncoded(responseHeaders)) {
            Log.i(HTTP_LOG_TAG, "<-- END HTTP (encoded body omitted)");
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    Log.i(HTTP_LOG_TAG, "");
                    Log.i(HTTP_LOG_TAG, "Couldn't decode the response body; charset is likely malformed.");
                    Log.i(HTTP_LOG_TAG, "<-- END HTTP");
                    return response;
                }
            }

            if (contentLength != 0) {
                Log.i(HTTP_LOG_TAG, "");
                Log.i(HTTP_LOG_TAG, buffer.clone().readString(charset));
                LogUtils.d(buffer.clone().readString(charset));
            }
            Log.i(HTTP_LOG_TAG, "<-- END HTTP (" + buffer.size() + "-byte body)");
        }
        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
