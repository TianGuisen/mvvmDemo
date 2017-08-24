package tgs.com.mvvm.core.retrofit;

import android.app.Activity;
import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import tgs.com.mvvm.utils.NetUtil;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.weight.RotateLoading;

/**
 * Created by 田桂森 on 2017/8/15.
 */
public abstract class MyObserver<T> implements Observer<T> {
    private Context context;
    private RotateLoading loading;
    
    /**
     * @param context 为null不显示toast
     * @param loading 为null不显示loading
     */
    public MyObserver(Activity context, RotateLoading loading) {
        this.context = context;
        this.loading = loading;
    }
    
    @Override
    public void onSubscribe(Disposable d) {
        if (!NetUtil.isConnected()) {
            if (null != context) ToastUtil.showToast("网络异常");
            return;
        } else if (loading != null) {
            loading.start();
        }
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
                ToastUtil.showToast("网络异常");
            } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                ToastUtil.showToast("请求超时,请稍后再试");
            } else if (e instanceof HttpException) {
                ToastUtil.showToast("服务器异常,请稍后再试");
            } else {
//                LogUtils.d(e);
            }
        }
        if (loading != null) {
            loading.stop();
        }
        //rxjava2传null的话会报这个空指针，因为后台可能传过来是成功但是data是null的情况，所以我暂时想到这样处理，也可以换成rxjava1。
        if ("onNext called with null. Null values are generally not allowed in 2.x operators and sources.".equals(e.getMessage())) {
            next(null);
        } else {
            error(e.getMessage());
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