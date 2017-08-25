package tgs.com.mvvm.view;

import android.os.Bundle;

import tgs.com.mvvm.R;
import tgs.com.mvvm.databinding.FgDynamicBinding;
import tgs.com.mvvm.view.Iview.ITest2;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.DynamicVM;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class DynamicFg extends BaseFragment<FgDynamicBinding> implements ITest2 {
    public static DynamicFg newInstance() {
        Bundle args = new Bundle();
        DynamicFg fragment = new DynamicFg();
        fragment.setArguments(args);
        return fragment;
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
