package tgs.com.mvvm.view;

import android.os.Bundle;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseFragment;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.FgRecommendBinding;
import tgs.com.mvvm.view.Iview.IRecommend;
import tgs.com.mvvm.vm.RecommendVM;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class RecommendFg extends BaseFragment<FgRecommendBinding> implements IRecommend {
    
    public static RecommendFg newInstance() {
        Bundle args = new Bundle();
        RecommendFg fragment = new RecommendFg();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        return new RecommendVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_recommend;
    }
}
