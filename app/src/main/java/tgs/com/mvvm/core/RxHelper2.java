package tgs.com.mvvm.core;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tgs.com.mvvm.base.BaseBean;

/**
 * Created by 田桂森 on 2017/3/25.
 */

public class RxHelper2 {
    /**
     * 订阅在io,观察在main,getJson()
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> ioMain() {
        return baseEntityObservable -> baseEntityObservable.flatMap(result -> {
            if (result.getCode() == 0) {
                return createDate(result.getData());
            } else {
                return Observable.error(new Exception(result.getMessage().toString()));
            }
        }).subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    /**
     * 数据流操作，转化线程 getDefalut()
     *
     * @param <T> 数据流操作对象
     * @return 转化过的数据
     */
    public static <T> ObservableTransformer<T, T> ioMain2() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    @NonNull
    private static <T> Observable<T> createDate(T data) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}