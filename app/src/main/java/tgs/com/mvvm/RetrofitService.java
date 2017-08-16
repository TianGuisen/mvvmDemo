package tgs.com.mvvm;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import tgs.com.mvvm.base.BaseBean;
import tgs.com.mvvm.bean.BannerInfo;

/**
 * Created by 田桂森 on 2017/4/17.
 */

public interface RetrofitService {
    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<BaseBean<List<BannerInfo>>> getBannerInfo();
}
