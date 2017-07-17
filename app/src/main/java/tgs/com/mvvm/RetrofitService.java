package tgs.com.mvvm;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tgs.com.mvvm.base.BaseBean;
import tgs.com.mvvm.bean.UserInfo;

/**
 * Created by 田桂森 on 2017/4/17.
 */

public interface RetrofitService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("user/login.do")
    Flowable<BaseBean<UserInfo>> login(@Field("username") String username, @Field("password") String password);
}
