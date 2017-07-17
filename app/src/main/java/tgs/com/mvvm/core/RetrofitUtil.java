package tgs.com.mvvm.core;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import tgs.com.mvvm.RetrofitService;
import tgs.com.mvvm.constant.NetConstant;

/**
 * Created by 田桂森 on 2016/11/30.
 */

public class RetrofitUtil {
    
    private static RetrofitService stringService;
    private static RetrofitService gsonService;
    
    private static RetrofitService createRetrofit(RetrofitParams params) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        for (Interceptor interceptor : params.interceptors) {
            builder.addInterceptor(interceptor);
        }
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES);
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(params.baseUrl)
                .addConverterFactory(params.converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(RetrofitService.class);
    }
    
    
    private static class RetrofitParams {
        private List<Interceptor> interceptors = new ArrayList();
        private String baseUrl = NetConstant.BASE_URL;
        private Converter.Factory converterFactory = ScalarsConverterFactory.create();
    }
    
    public static RetrofitService getDefalut() {
        if (stringService == null) {
            synchronized (RetrofitUtil.class) {
                if (stringService == null) {
                    RetrofitParams retrofitParams = new RetrofitParams();
                    retrofitParams.interceptors.add(new ParamInterceptor());
                    retrofitParams.interceptors.add(new LoggerInterceptor2());
                    stringService = createRetrofit(retrofitParams);
                }
            }
        }
        return stringService;
    }
    
    public static RetrofitService getJson() {
        if (gsonService == null) {
            synchronized (RetrofitUtil.class) {
                if (gsonService == null) {
                    RetrofitParams retrofitParams = new RetrofitParams();
                    retrofitParams.interceptors.add(new ParamInterceptor());
                    retrofitParams.interceptors.add(new LoggerInterceptor2());
                    retrofitParams.converterFactory = GsonConverterFactory.create();
                    gsonService = createRetrofit(retrofitParams);
                }
            }
        }
        return gsonService;
    }
    
}
