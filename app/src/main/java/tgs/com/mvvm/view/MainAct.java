package tgs.com.mvvm.view;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseActivity;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.ActMainBinding;
import tgs.com.mvvm.view.Iview.ILogin;
import tgs.com.mvvm.vm.MainVM;


public class MainAct extends BaseActivity<ActMainBinding> implements ILogin {
    
    @Override
    protected int setBR() {
        return BR.mainVM;
    }
    
    @Override
    protected BaseVM setVM() {
        return new MainVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.act_main;
    }
    
}
