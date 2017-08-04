package tgs.com.mvvm.base;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import me.yokeyword.fragmentation.SupportActivity;
import tgs.com.mvvm.ActivityManager;
import tgs.com.mvvm.R;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.weight.RotateLoading;

public abstract class BaseActivity<D extends ViewDataBinding> extends SupportActivity implements BaseInterface {
    private D bind;
    private BaseVM vm;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        bind = DataBindingUtil.setContentView(this, setLayout());
        vm = setVM();
        bind.setVariable(setBR(), vm);
        init();
        vm.init();
    }
    //autolayout适配用
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals("FrameLayout")) {
            view = new AutoFrameLayout(context, attrs);
        }
        if (name.equals("LinearLayout")) {
            view = new AutoLinearLayout(context, attrs);
        }
        if (name.equals("RelativeLayout")) {
            view = new AutoRelativeLayout(context, attrs);
        }
        if (view != null) return view;
        return super.onCreateView(name, context, attrs);
    }
    
    /**
     * ui绘制完成后调用
     */
    public void init() {
        
    }
    
    protected abstract int setBR();
    
    protected abstract BaseVM setVM();
    
    
    protected abstract int setLayout();
    
    protected D getBind() {
        return bind;
    }
    
    @Override
    protected void onPause() {
        super.onPause();
    }
    
    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().remove(this);
        vm.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }
    
    @Override
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
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
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }
    
    @Override
    public void toastMessage(String message) {
        ToastUtil.showToast(this, message);
    }
    
    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }
    
    @Override
    public RotateLoading getLoading() {
        return (RotateLoading) findViewById(R.id.loading);
    }
}
