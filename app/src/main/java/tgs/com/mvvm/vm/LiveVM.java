package tgs.com.mvvm.vm;

import java.util.ArrayList;
import java.util.List;

import tgs.com.mvvm.R;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.core.MyObserver;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.core.RxHelper2;
import tgs.com.mvvm.core.retrofit.RetrofitUtil;
import tgs.com.mvvm.view.Iview.ILive;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class LiveVM extends BaseVM<ILive> {
    public List<BannerInfo> list = new ArrayList<>();
    public ReplyCommand<Object> itemClick = new ReplyCommand<Object>((itemInfo, view, position) -> {
        i.toastMessage("点击的是item:" + itemInfo.toString() + "  位置:" + position);
    });
    public ReplyCommand<Object> childClick = new ReplyCommand<Object>((itemInfo, view, position) -> {
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
                        list.clear();
                        list.addAll(bannerInfos);
                    }
                });

//        RetrofitUtil.getJson().getRecommendedInfo()
//                .compose(RxHelper2.ioMain2())
//                .subscribe(new MyObserver<String>(null, null) {
//                    @Override
//                    protected void next(String s) {
//                        List<RecommendInfo.ResultBean> result = JsonUtils.fromJson(s, RecommendInfo.class).getResult();
//                        LogUtils.d(result);
//                        list.clear();
//                        list.addAll(result);
//                    }
//                });
        
        
    }
}
