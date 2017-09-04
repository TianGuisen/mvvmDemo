package tgs.com.mvvm.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import me.yokeyword.fragmentation.SupportFragment;
import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.weight.RotateLoading;


/**
 * Created by 田桂森 on 2016/11/9.
 */
public abstract class BaseFragment<D extends ViewDataBinding> extends SupportFragment implements BaseInterface {
    
    private D bind;
    private BaseVM vm;
    private RotateLoading loading;
    private ImmersionBar mImmersionBar;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, setLayout(), container, false);
        bind.setVariable(BR.vm, vm = setVM());
        return bind.getRoot();
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            View titleBar = view.findViewById(setTitleBar());
            if (titleBar != null)
                ImmersionBar.setTitleBar(getActivity(), titleBar);
            View statusBarView = view.findViewById(setStatusBarView());
            if (statusBarView != null)
                ImmersionBar.setStatusBarView(getActivity(), statusBarView);
        }
        init();
        vm.init();
        setUserVisibleHint(true);
    }
    
    protected int setTitleBar() {
        return R.id.toolbar;
    }
    
    protected int setStatusBarView() {
        return R.id.status;
    }
    
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        vm.lazyInit();
    }
    
    protected abstract BaseVM setVM();
    
    protected D getBind() {
        return bind;
    }
    
    protected abstract int setLayout();
    
    protected void init() {
        
    }
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //如果要在Fragment单独使用沉浸式，请在onSupportVisible实现沉浸式
        if (isImmersionBarEnabled()) {
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.navigationBarWithKitkatEnable(false).init();
        }
    }
    
    private boolean isImmersionBarEnabled() {
        return false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        vm.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }
    
    public MainAct getMainAct() {
        return (MainAct) _mActivity;
    }
    
    public MainFg getMainFg() {
        return findFragment(MainFg.class);
    }
    
    @Override
    public RotateLoading getLoading() {
        if (loading == null) {
            loading = getMainFg().getLoading();
        }
        return loading;
    }
    
    @Override
    public void toastMessage(String message) {
        ToastUtil.showToast(message);
    }
    
    @Override
    public void startLoading() {
        getLoading().start();
    }
    
    @Override
    public void stopLoading() {
        getLoading().stop();
    }
    
    @Override
    public void finish() {
        getActivity().finish();
    }
}
