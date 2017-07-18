package tgs.com.mvvm.core;


import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.utils.NetUtil;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.weight.RotateLoading;

/**
 * Created by 田桂森 on 2017/3/25.
 */

public abstract class RxSubscribe<T> implements Subscriber<T> {
    private Context context;
    private RotateLoading loading;
    
    public RxSubscribe(BaseInterface baseInterface) {
        
        this.context =baseInterface.getBaseActivity();
        this.loading = baseInterface.getLoading();
    }
    
    @Override
    public void onSubscribe(Subscription s) {
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
                error(e.getMessage());
            }
        }
        if (loading != null) {
            loading.stop();
        }
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
