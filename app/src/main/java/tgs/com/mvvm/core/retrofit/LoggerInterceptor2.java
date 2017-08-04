package tgs.com.mvvm.core.retrofit;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * Created by 田桂森 on 2017/5/18.
 *  联网日志拦截器，过滤掉一些无用的信息
 */

public class LoggerInterceptor2 implements Interceptor {
    
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request orgRequest = chain.request();
        Response response = chain.proceed(orgRequest);
        RequestBody body = orgRequest.body();
        StringBuilder sb = new StringBuilder();
        if (orgRequest.method().equals("POST") && body instanceof FormBody) {
            FormBody body1 = (FormBody) body;
            for (int i = 0; i < body1.size(); i++) {
                sb.append(body1.encodedName(i) + "=" + body1.encodedValue(i) + ",");
            }
            sb.delete(sb.length() - 1, sb.length());
            Log.e("retrofit", "post请求体:{" + sb.toString() + "}");
        }
        Log.e("retrofit", "code=" + response.code() + "|method=" + orgRequest.method() + "|url="
                + orgRequest.url());
        
        //返回值
        Headers responseHeaders = response.headers();
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        if (!bodyEncoded(responseHeaders)) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }
            if (contentLength != 0) {
                Log.e("retrofit", buffer.clone().readString(charset));
            }
        }
        return response;
    }
    
    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}