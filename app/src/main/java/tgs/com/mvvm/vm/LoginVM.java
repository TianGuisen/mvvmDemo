package tgs.com.mvvm.vm;


import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.core.RetrofitUtil;
import tgs.com.mvvm.core.RxHelper;
import tgs.com.mvvm.core.RxSubscribe;
import tgs.com.mvvm.view.Iview.ILogin;
import tgs.com.mvvm.view.MainAct;

/**
 * Created by 田桂森 on 2017/4/17.
 */

public class LoginVM extends BaseVM<ILogin> {
    public LoginVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    public final ObservableField<String> username = new ObservableField("");
    public final ObservableField<String> password = new ObservableField("");
    public final ObservableField<String> urlImg = new ObservableField("https://avatars6.githubusercontent.com/u/19662707?v=4&s=40");
    public final ObservableInt localImg = new ObservableInt(R.drawable.ic_nav_star);
    
    @Override
    public void init() {
        username.set("10000016");
        password.set("112233");
    }
    
    public final ReplyCommand imgClick = new ReplyCommand(() -> {
        localImg.set(R.drawable.test_img1);
    });
    public final ReplyCommand loginClick = new ReplyCommand(() -> {
        RetrofitUtil.getJson().login(username.get(), password.get()).compose(RxHelper.ioMain())
                .subscribe(new RxSubscribe<String>(i, true) {
                    @Override
                    protected void next(String token) {
                        i.openActivity(MainAct.class);
                        i.finish();
                    }
                    
                    @Override
                    protected void error(String message) {
                        i.openActivity(MainAct.class);
                        i.finish();
                    }
                });

//        RetrofitUtil.getJson().login2(username.get(), password.get()).compose(RxHelper.ioMain())
//                .subscribe(new RxSubscribe<UserInfo>(i) {
//                    @Override
//                    protected void next(UserInfo userInfo) {
//                    }
//                });
        
    });
}
