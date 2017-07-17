package tgs.com.mvvm.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tgs.com.mvvm.ActivityManager;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.weight.RotateLoading;


/**
 * Created by 田桂森 on 2016/11/9.
 */
public abstract class BaseFragment<D extends ViewDataBinding> extends Fragment implements BaseInterface {
    
    private D bind;
    private BaseVM viewModel;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, setLayout(), container, false);
        bind.setVariable(setBR(), viewModel = setVM());
        init();
        viewModel.init();
        setUserVisibleHint(true);
        return bind.getRoot();
    }
    
    protected abstract BaseVM setVM();
    
    protected abstract int setBR();
    
    protected D getBind() {
        return bind;
    }
    
    protected abstract int setLayout();
    
    protected void init() {
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
    
    @Override
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }
    
    @Override
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }
    
    @Override
    public void openActivityForResult(Class<?> pClass, int requestCode) {
        openActivityForResult(pClass, null, requestCode);
    }
    
    @Override
    public void openActivityForResult(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }
    
    @Override
    public void toastMessage(String message) {
        ToastUtil.showToast(getActivity(), message);
    }
    
    @Override
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
    
    @Override
    public RotateLoading getLoading() {
        BaseActivity activity = (BaseActivity) ActivityManager.getInstance().currentActivity();
        return activity.getLoading();
    }
    
}