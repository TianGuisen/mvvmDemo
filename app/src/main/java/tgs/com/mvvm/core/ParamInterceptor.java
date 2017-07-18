package tgs.com.mvvm.core;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 田桂森 on 2017/4/17.
 * 参数拦截器，加token之类。
 */
public class ParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orgRequest = chain.request();
        HttpUrl url = orgRequest.url().newBuilder()
                .addQueryParameter("device_id", "123456789")
                .build();
        Request newOrgRequest = chain.request().newBuilder()
                .url(url)
                .build();
        return chain.proceed(newOrgRequest);
    }
}
