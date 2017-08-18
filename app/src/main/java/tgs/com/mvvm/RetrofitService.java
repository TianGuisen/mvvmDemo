package tgs.com.mvvm;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tgs.com.mvvm.bean.BaseBean;
import tgs.com.mvvm.bean.BannerInfo;
import tgs.com.mvvm.bean.VideoDetailsInfo;

/**
 * Created by 田桂森 on 2017/4/17.
 */

public interface RetrofitService {
    /**
     * 首页推荐banner
     */
    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<BaseBean<List<BannerInfo>>> getBannerInfo();
    
    /**
     * 首页推荐数据
     */
    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<String> getRecommendedInfo();
    
    /**
     * 视频详情数据
     */
    @GET("x/view?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=1206255541e2648c1badb87812458046&ts=1478349831")
    Observable<BaseBean<VideoDetailsInfo>> getVideoDetails(@Query("aid") int aid);
}
