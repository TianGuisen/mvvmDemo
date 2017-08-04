package tgs.com.mvvm.core;


import android.app.Activity;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import tgs.com.mvvm.utils.NetUtil;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.weight.RotateLoading;

/**
 * Created by 田桂森 on 2017/3/25.
 */

public abstract class RxSubscribe<T> implements Subscriber<T> {
    private Context context;
    private RotateLoading loading;
    
    /**
     * @param context 为null不显示toast
     * @param loading 为null不显示loading
     */
    public RxSubscribe(Activity context, RotateLoading loading) {
        this.context = context;
        this.loading = loading;
    }
    
    @Override
    public void onSubscribe(Subscription s) {
        LogUtils.d(s);
        if (!NetUtil.isConnected()) {
            if (null != context) ToastUtil.showToast(context, "网络异常");
            return;
        } else if (loading != null) {
            loading.start();
        }
        s.request(1);
    }
    
    @Override
    public void onComplete() {
        if (loading != null) {
            loading.stop();
        }
        complete();
    }
    
    @Override
    public void onError(Throwable e) {
        if (null != context) {
            if (!NetUtil.isConnected()) {
                ToastUtil.showToast(context, "网络异常");
            } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                ToastUtil.showToast(context, "请求超时,请稍后再试");
            } else if (e instanceof HttpException) {
                ToastUtil.showToast(context, "服务器异常,请稍后再试");
            } else {
                LogUtils.d(e);
            }
        }
        if (loading != null) {
            loading.stop();
        }
        error(e.getMessage());
    }
    
    @Override
    public void onNext(T t) {
        next(t);
    }
    
    protected abstract void next(T t);
    
    
    protected void error(String message) {
        
    }
    
    protected void complete() {
    }
}
