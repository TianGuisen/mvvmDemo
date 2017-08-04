package tgs.com.mvvm.view;

import android.os.Bundle;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseFragment;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.FgZoneBinding;
import tgs.com.mvvm.view.Iview.IZone;
import tgs.com.mvvm.vm.ZoneVM;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class ZoneFg extends BaseFragment<FgZoneBinding> implements IZone {
    
    public static ZoneFg newInstance() {
        Bundle args = new Bundle();
        ZoneFg fragment = new ZoneFg();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        return new ZoneVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_zone;
    }
    
}
