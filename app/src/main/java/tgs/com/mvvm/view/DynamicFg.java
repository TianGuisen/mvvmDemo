package tgs.com.mvvm.view;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseFragment;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.FgDynamicBinding;
import tgs.com.mvvm.view.Iview.IRecommend;
import tgs.com.mvvm.vm.DynamicVM;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class DynamicFg extends BaseFragment<FgDynamicBinding> implements IRecommend {
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        return new DynamicVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_dynamic;
    }
}
