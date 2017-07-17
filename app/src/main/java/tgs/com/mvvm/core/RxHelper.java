package tgs.com.mvvm.core;


import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tgs.com.mvvm.base.BaseBean;
/**
 * Created by 田桂森 on 2017/3/25.
 * 转化线程和处理结果
 */

public class RxHelper {
    /**
     * 订阅在io,观察在main
     */
    public static <T> FlowableTransformer<BaseBean<T>, T> ioMain() {
        return baseEntityObservable -> baseEntityObservable.flatMap(result -> {
            if (result.getStatus() == 0) {
                return createDate(result.getData());
            } else {
                return Flowable.error(new Exception(result.getMessage().toString()));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    public static <T> FlowableTransformer<T, T> ioMain2() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    
    private static <T> Flowable<T> createDate(T data) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> e) throws Exception {
                try {
                    e.onNext(data);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
