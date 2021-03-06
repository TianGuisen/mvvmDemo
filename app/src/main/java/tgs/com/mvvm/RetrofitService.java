package tgs.com.mvvm;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.bean.BaseBean;
import tgs.com.mvvm.bean.RecommendInfo;
import tgs.com.mvvm.bean.VideoDetailsInfo;
import tgs.com.mvvm.bean.VideoPlayUrlInfo;

/**
 * Created by 田桂森 on 2017/4/17.
 */

public interface RetrofitService {
    //rxjava2新加了一个支持背压的Flowable,但是一般情况并用不到这个功能
    /**
     * 首页推荐banner
     */
    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<BaseBean<List<BannerInfo>>> getBannerInfo();
    
    /**
     * 首页推荐数据
     */
    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<RecommendInfo> getRecommendedInfo();
    
    /**
     * 视频详情数据
     */
    @GET("x/view?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=1206255541e2648c1badb87812458046&ts=1478349831")
    Observable<BaseBean<VideoDetailsInfo>> getVideoDetails(@Query("aid") String aid);
   
    @GET()
    Observable<VideoPlayUrlInfo> getVideoUrl(@Url String url);
}
