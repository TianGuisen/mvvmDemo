package tgs.com.mvvm.vm;


import android.databinding.ObservableField;

import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.bean.UserInfo;
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
    
    @Override
    public void init() {
        username.set("aaa");
        password.set("aaa");
    }
    
    public final ReplyCommand loginClick = new ReplyCommand(() -> {
        RetrofitUtil.getJson().login(username.get(), password.get()).compose(RxHelper.ioMain())
                .subscribe(new RxSubscribe<UserInfo>(i) {
                    @Override
                    protected void next(UserInfo userInfo) {
                        i.openActivity(MainAct.class);
                        i.finish();
                    }
    
//                    @Override
//                    protected void error(String message) {
//                        i.openActivity(MainAct.class);
//                        i.finish();
//                    }
                });
    });
}
