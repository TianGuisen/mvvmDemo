package tgs.com.mvvm.view;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseFragment;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.FgLiveBinding;
import tgs.com.mvvm.view.Iview.ILive;
import tgs.com.mvvm.vm.LiveVM;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class LiveFg extends BaseFragment<FgLiveBinding> implements ILive {
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        return new LiveVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_live;
    }
}
