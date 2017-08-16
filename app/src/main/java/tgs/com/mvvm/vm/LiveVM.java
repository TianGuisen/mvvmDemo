package tgs.com.mvvm.vm;

import com.apkfuns.logutils.LogUtils;

import java.util.List;

import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.core.RxHelper2;
import tgs.com.mvvm.core.MyObserver;
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
                .compose(RxHelper2.ioMain())
                .subscribe(new MyObserver<List<BannerInfo>>(i.getBaseActivity(), null) {
                    @Override
                    protected void next(List<BannerInfo> bannerInfos) {
                        LogUtils.d(bannerInfos);
                    }
                });
    }
}
