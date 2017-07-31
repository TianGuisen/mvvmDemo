package tgs.com.mvvm.view;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseActivity;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.ActLoginBinding;
import tgs.com.mvvm.view.Iview.ILogin;
import tgs.com.mvvm.vm.LoginVM;


public class LoginAct extends BaseActivity<ActLoginBinding> implements ILogin {
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        return new LoginVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.act_login;
    }
}
