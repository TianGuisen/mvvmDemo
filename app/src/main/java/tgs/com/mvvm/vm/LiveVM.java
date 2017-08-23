package tgs.com.mvvm.vm;

import java.util.ArrayList;
import java.util.List;

import tgs.com.mvvm.R;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.bean.RecommendInfo;
import tgs.com.mvvm.core.MyObserver;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.core.RxHelper2;
import tgs.com.mvvm.core.retrofit.RetrofitUtil;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.ILive;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class LiveVM extends BaseVM<ILive> {
    public List<String> listBannerImgUrl = new ArrayList<>();
    public List<BannerInfo> listBanner = new ArrayList<>();
    public List<RecommendInfo.ResultBean.BodyBean> listRecommended = new ArrayList<>();
    public ReplyCommand<Object> itemClick = new ReplyCommand<>((itemInfo, view, position) -> {
        i.toastMessage("点击的是item:" + itemInfo.toString() + "  位置:" + position);
    });
    public ReplyCommand<Object> childClick = new ReplyCommand<>((itemInfo, view, position) -> {
        if (view.getId() == R.id.tv_name) {
            i.toastMessage("点击的是name:" + itemInfo.toString() + "  位置:" + position);
        } else if (view.getId() == R.id.tv_age) {
            i.toastMessage("点击的是age:" + itemInfo.toString() + "  位置:" + position);
        }
    });
    
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
                        listBanner.clear();
                        listBannerImgUrl.clear();
                        listBanner.addAll(bannerInfos);
                        for (BannerInfo bannerInfo : bannerInfos) {
                            listBannerImgUrl.add(bannerInfo.getImage());
                        }
                        RetrofitUtil.getJson().getRecommendedInfo()
                                .compose(RxHelper2.ioMain2())
                                .subscribe(new MyObserver<RecommendInfo>(null, null) {
                                    @Override
                                    protected void next(RecommendInfo recommendInfo) {
                                        
                                        for (RecommendInfo.ResultBean resultBean : recommendInfo.getResult()) {
                                            for (RecommendInfo.ResultBean.BodyBean bodyBean : resultBean.getBody()) {
                                                listRecommended.add(bodyBean);
                                            }
                                        }
                                        i.initAdapter();
                                    }
                                });
                    }
                });
    }
}
