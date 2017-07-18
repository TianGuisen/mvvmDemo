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
     *
     * @param account  用户名
     * @param password 密码
     * @return token
     * {"code":1,"message":"成功","data":"7f0cda8519f0e0456c6f89dbd979da9d","success":true,"error":false}
     */
    @FormUrlEncoded
    @POST("login.json")
    Flowable<BaseBean<String>> login(@Field("account") String account, @Field("password") String password);
    
    /**
     * 登录
     *
     * @param account  用户名
     * @param password 密码
     * @return 获取用户信息
     * {"code":1,"data":{"age":10,"name":"田桂森"},"error":false,"message":"成功","success":true}
     */
    @FormUrlEncoded
    @POST("login.json2")
    Flowable<BaseBean<UserInfo>> login2(@Field("account") String account, @Field("password") String password);
}
