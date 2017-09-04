package tgs.com.mvvm.core.retrofit;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tgs.com.mvvm.weight.player.BiliDanmukuCompressionTools;
import tgs.com.mvvm.weight.player.BiliDanmukuParser;


/**
 * Created by lwlizhe on 2017/6/30.
 * 邮箱：624456662@qq.com
 */
public class BiliDanmukuDownloadUtil {

    public static Flowable<BaseDanmakuParser> downloadXML(final String uri) {
        Flowable<BaseDanmakuParser> flowable = null;

        flowable = Flowable.create(new FlowableOnSubscribe<BaseDanmakuParser>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<BaseDanmakuParser> emitter) throws Exception {
                if (TextUtils.isEmpty(uri)) {
                    emitter.onNext(new BaseDanmakuParser() {
                        @Override
                        protected IDanmakus parse() {
                            return new Danmakus();
                        }
                    });
                }
                ILoader loader = null;

                OkHttpClient client=new OkHttpClient.Builder().build();

                Request request = new Request.Builder().url(uri).build();

                Response response = client.newCall(request).execute();

                if(response.isSuccessful()){

                    InputStream stream = new ByteArrayInputStream(BiliDanmukuCompressionTools.
                            decompressXML(response.body().bytes()));

                    loader = DanmakuLoaderFactory.
                            create(DanmakuLoaderFactory.TAG_BILI);
                    loader.load(stream);

                    BaseDanmakuParser parser = new BiliDanmukuParser();
                    assert loader != null;
                    IDataSource<?> dataSource = loader.getDataSource();
                    parser.load(dataSource);
                    emitter.onNext(parser);

                }else{
                    emitter.onNext(new BaseDanmakuParser() {
                        @Override
                        protected IDanmakus parse() {
                            return new Danmakus();
                        }
                    });
                }
//                try {
//
//
//
//
//
//                    HttpConnection.Response rsp = (HttpConnection.Response)
//                            Jsoup.connect(uri).timeout(20000).execute();
//                    InputStream stream = new ByteArrayInputStream(BiliDanmukuCompressionTools.
//                            decompressXML(rsp.bodyAsBytes()));
//                    loader = DanmakuLoaderFactory.
//                            create(DanmakuLoaderFactory.TAG_BILI);
//                    loader.load(stream);
//                } catch (IOException | DataFormatException | IllegalDataException e) {
//                    e.printStackTrace();
//                }
//                BaseDanmakuParser parser = new BiliDanmukuParser();
//                assert loader != null;
//                IDataSource<?> dataSource = loader.getDataSource();
//                parser.load(dataSource);
//                emitter.onNext(parser);

            }
        }, BackpressureStrategy.ERROR);

        return flowable;
    }


//    public static Observable<BaseDanmakuParser> downloadXML(final String uri) {
//        return Observable.create(new Observable.OnSubscribe<BaseDanmakuParser>() {
//            @Override
//            public void call(rx.Subscriber<? super BaseDanmakuParser> subscriber) {
//                if (TextUtils.isEmpty(uri)) {
//                    subscriber.onNext(new BaseDanmakuParser() {
//                        @Override
//                        protected IDanmakus parse() {
//                            return new Danmakus();
//                        }
//                    });
//                }
//                ILoader loader = null;
//                try {
//                    HttpConnection.Response rsp = (HttpConnection.Response)
//                            Jsoup.connect(uri).timeout(20000).execute();
//                    InputStream stream = new ByteArrayInputStream(BiliDanmukuCompressionTools.
//                            decompressXML(rsp.bodyAsBytes()));
//                    loader = DanmakuLoaderFactory.
//                            create(DanmakuLoaderFactory.TAG_BILI);
//                    loader.load(stream);
//                } catch (IOException | DataFormatException | IllegalDataException e) {
//                    e.printStackTrace();
//                }
//                BaseDanmakuParser parser = new BiliDanmukuParser();
//                assert loader != null;
//                IDataSource<?> dataSource = loader.getDataSource();
//                parser.load(dataSource);
//                subscriber.onNext(parser);
//            }
//        }).subscribeOn(Schedulers.io());
//    }
}
