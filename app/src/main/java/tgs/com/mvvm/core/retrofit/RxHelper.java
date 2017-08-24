package tgs.com.mvvm.core.retrofit;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tgs.com.mvvm.bean.BaseBean;

/**
 * Created by 田桂森 on 2017/3/25.
 */

public class RxHelper {
    /**
     * 订阅在io,观察在main,getJson()
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> ioMain() {
        return baseEntityObservable -> baseEntityObservable.flatMap(result -> {
            
//             如果后台返回message就这样。
//            if (result.getCode() == 0) {
//                return createDate(result.getData());
//            } else {
//                return Observable.error(new Exception(result.getMessage().toString()));
//            }
            
            //如果后台不返回message，就需要向下面这样自己定义错误message。
            switch (result.getCode()) {
                case 0:
                    return createDate(result.getData());
                case 101:
                    return Observable.error(new Exception("错误1"));
                case 102:
                    return Observable.error(new Exception("错误2"));
                default:
                    return Observable.error(new Exception(result.getMessage().toString()));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    /**
     * 数据流操作，转化线程,外层没有BaseBean用这个
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
