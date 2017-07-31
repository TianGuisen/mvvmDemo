package tgs.com.mvvm.view;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseFragment;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.FgRecyclerBinding;
import tgs.com.mvvm.view.Iview.IRecommend;
import tgs.com.mvvm.vm.RecommendVM;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class RecommendFg extends BaseFragment<FgRecyclerBinding> implements IRecommend {
    
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
