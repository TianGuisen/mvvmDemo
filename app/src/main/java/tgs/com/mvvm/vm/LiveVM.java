package tgs.com.mvvm.vm;

import com.apkfuns.logutils.LogUtils;

import java.util.List;

import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.core.RxHelper;
import tgs.com.mvvm.core.RxSubscribe;
import tgs.com.mvvm.core.retrofit.RetrofitUtil;
import tgs.com.mvvm.view.Iview.ILive;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class LiveVM extends BaseVM<ILive> {
    public LiveVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    @Override
    public void lazyInit() {
        RetrofitUtil.getJson().getBannerInfo()
                .compose(RxHelper.ioMain())
                .subscribe(new RxSubscribe<List<BannerInfo>>(i.getBaseActivity(), null) {
                    @Override
                    protected void next(List<BannerInfo> s) {
                        LogUtils.d(s);
                    }
                });
        
//        RetrofitUtil.getJson().getBannerInfo()
//                .compose(RxHelper.ioMain())
//                .subscribe(new Subscriber<List<BannerInfo>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        LogUtils.d(s);
//                    }
//    
//                    @Override
//                    public void onNext(List<BannerInfo> bannerInfos) {
//                        LogUtils.d(bannerInfos);
//                    }
//    
//                    @Override
//                    public void onError(Throwable t) {
//                        LogUtils.d(t);
//                    }
//    
//                    @Override
//                    public void onComplete() {
//                        LogUtils.d("onComplete");
//                    }
//                });
        
//        RetrofitUtil.getJson().getBannerInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BaseBean<List<BannerInfo>>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        s.request(1);
//                    }
//                    
//                    @Override
//                    public void onNext(BaseBean<List<BannerInfo>> listBaseBean) {
//                        LogUtils.d(listBaseBean);
//                    }
//                    
//                    @Override
//                    public void onError(Throwable t) {
//                        LogUtils.d(t);
//                    }
//                    
//                    @Override
//                    public void onComplete() {
//                        LogUtils.d("onComplete");
//                    }
//                });
    }
}
