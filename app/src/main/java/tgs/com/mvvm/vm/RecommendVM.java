package tgs.com.mvvm.vm;

import android.view.View;

import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import tgs.com.mvvm.R;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.bean.BaseBean;
import tgs.com.mvvm.bean.RecommendInfo;
import tgs.com.mvvm.core.Reply;
import tgs.com.mvvm.core.retrofit.RetrofitUtil;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.IRecommend;
import tgs.com.mvvm.weight.adapter.BaseAdapter;
import tgs.com.mvvm.weight.adapter.RecommendAdapter;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class RecommendVM extends BaseVM<IRecommend> implements RecommendAdapter.OnBannerClickListener, BaseAdapter.onItemClickLisener {
    public List<String> listBannerImgUrl = new ArrayList<>();
    public List<BannerInfo> listBanner = new ArrayList<>();
    public List<RecommendInfo.ResultBean.HeadBean> listRecommendedHead = new ArrayList<>();
    public List<RecommendInfo.ResultBean.BodyBean> listRecommended = new ArrayList<>();
    
    @Override
    public void lazyInit() {
        loadData();
    }
    
    public RecommendVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    public Reply refresh = new Reply<Integer>(o -> {
        loadData();
    });
    
    public void loadData() {
        Observable.zip(RetrofitUtil.getJson().getBannerInfo(), RetrofitUtil.getJson().getRecommendedInfo(), new BiFunction<BaseBean<List<BannerInfo>>, RecommendInfo, Object>() {
            @Override
            public List apply(@NonNull BaseBean<List<BannerInfo>> listBaseBean, @NonNull RecommendInfo recommendInfo) throws Exception {
                List objects = new ArrayList<>();
                objects.add(listBaseBean);
                objects.add(recommendInfo);
                return objects;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    List objects = (List) o;
                    //轮播图的
                    List<BannerInfo> bannerInfos = ((BaseBean<List<BannerInfo>>) objects.get(0)).getData();
                    listBanner.clear();
                    listBannerImgUrl.clear();
                    listBanner.addAll(bannerInfos);
                    for (BannerInfo bannerInfo : bannerInfos) {
                        listBannerImgUrl.add(bannerInfo.getImage());
                    }
                    
                    //其他
                    RecommendInfo recommendInfo = ((RecommendInfo) objects.get(1));
                    for (RecommendInfo.ResultBean resultBean : recommendInfo.getResult()) {
                        for (RecommendInfo.ResultBean.BodyBean bodyBean : resultBean.getBody()) {
                            listRecommended.add(bodyBean);
                        }
                        listRecommendedHead.add(resultBean.getHead());
                    }
                    listRecommendedHead.get(0).setImg(R.drawable.ic_header_hot);
                    listRecommendedHead.get(1).setImg(R.drawable.ic_category_bangumi);
                    listRecommendedHead.get(2).setImg(R.drawable.ic_head_live);
                    listRecommendedHead.get(3).setImg(R.drawable.ic_category_anim);
                    listRecommendedHead.get(4).setImg(R.drawable.ic_category_music);
                    listRecommendedHead.get(5).setImg(R.drawable.ic_category_dance);
                    listRecommendedHead.get(6).setImg(R.drawable.ic_category_game);
                    listRecommendedHead.get(7).setImg(R.drawable.ic_category_guichu);
                    listRecommendedHead.get(8).setImg(R.drawable.ic_category_tech);
                    listRecommendedHead.get(9).setImg(R.drawable.ic_header_activity_center);
                    listRecommendedHead.get(10).setImg(R.drawable.ic_category_life);
                    listRecommendedHead.get(11).setImg(R.drawable.ic_category_fashion);
                    listRecommendedHead.get(12).setImg(R.drawable.ic_category_entertain);
                    listRecommendedHead.get(13).setImg(R.drawable.ic_category_soap);
                    listRecommendedHead.get(14).setImg(R.drawable.ic_category_movie);
                    listRecommendedHead.get(15).setImg(R.drawable.ic_header_topic);
                }, throwable -> {
                    LogUtils.d(throwable);
                    i.refreshComplete(true, false);
                }, () -> {
                    LogUtils.d("com");
                    i.refreshComplete(true, true);
                });
    }
    
    @Override
    public void bannerClick(int position) {
        i.toastMessage("点击的是轮播图" + position + "  内容为" + listBanner);
    }
    
    @Override
    public void itemClick(Object bean, View view, int position) {
        if (view.getId() == R.id.card_standard) {
            RecommendInfo.ResultBean.BodyBean bodyBean = (RecommendInfo.ResultBean.BodyBean) bean;
            LogUtils.d(bodyBean);
            i.startVideoDetailsFg(bodyBean.getParam(),bodyBean.getCover());
            
            
        }
        i.toastMessage("点击的是item:" + bean.toString() + "  位置:" + position);
    }
}
