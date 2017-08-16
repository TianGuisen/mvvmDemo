package tgs.com.mvvm.core;


;

/**
 * Created by 田桂森 on 2017/3/25.
 * 转化线程和处理结果
 */
//public class RxHelper {
//
//    /**
//     * 订阅在io,观察在main
//     */
//    public static <T> FlowableTransformer<BaseBean<T>, T> ioMain() {
//        return baseEntityObservable -> baseEntityObservable.flatMap(result -> {
//            if (result.getCode() == 0) {
//                return createDate(result.getData());
//            } else {
//                return Observable.error(new Exception(result.getMessage().toString()));
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//    public static <T> Observable.Transformer<T, T> ioMain2() {
//        return tObservable -> tObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//    /**
//     * 需要加上subscribeOn()
//     */
//    public static <T> Observable.Transformer<BaseBean<T>, T> onDefault() {
//        return baseEntityObservable -> baseEntityObservable.flatMap(result -> {
//            if (result.getCode() >= 1) {
//                return createDate(result.getData());
//            } else {
//                return Observable.error(new Exception(result.getMessage()));
//            }
//        });
//    }
//
//    private static <T> Observable<T> createDate(T data) {
//        return Observable.create(subscriber -> {
//            try {
//                subscriber.onNext(data);
//                subscriber.onCompleted();
//            } catch (Exception e) {
//                subscriber.onError(e);
//            }
//        });
//    }
// 
//
//}

//public class RxHelper {
//    /**
//     * 订阅在io,观察在main
//     */
//    public static <T> FlowableTransformer<BaseBean<T>, T> ioMain() {
//
//        return baseEntityObservable -> baseEntityObservable.flatMap(result -> {
//            if (result.getCode() == 0) {
//                return createDate(result.getData());
//            } else {
//                return Flowable.error(new Exception(result.getMessage().toString()));
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static <T> FlowableTransformer<T, T> ioMain2() {
//        return new FlowableTransformer<T, T>() {
//            @Override
//            public Publisher<T> apply(Flowable<T> upstream) {
//                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//    private static <T> Flowable<T> createDate(T data) {
//        return Flowable.create(new FlowableOnSubscribe<T>() {
//            @Override
//            public void subscribe(FlowableEmitter<T> fe) {
//                try {
//                    fe.onNext(data);
//                    fe.onComplete();
//                } catch (Exception e) {
//                    fe.onError(e);
//                }
//            }
//        }, BackpressureStrategy.BUFFER);
//    }
//}
