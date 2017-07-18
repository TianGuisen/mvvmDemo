package tgs.com.mvvm.base;

import android.os.Bundle;

import tgs.com.mvvm.weight.RotateLoading;


/**
 * Created by 田桂森 on 2017/4/20.
 */

public interface BaseInterface {
    void toastMessage(String message);
    
    void openActivity(Class<?> pClass);
    void openActivity(Class<?> pClass, Bundle pBundle);
    void openActivityForResult(Class<?> pClass, int requestCode);
    void openActivityForResult(Class<?> pClass, Bundle pBundle, int requestCode);
    
    RotateLoading getLoading();
    
    BaseActivity getBaseActivity();
    
    void finish();
    
}
