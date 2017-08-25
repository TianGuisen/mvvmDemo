package tgs.com.mvvm.view.Iview;

import tgs.com.mvvm.weight.RotateLoading;


/**
 * Created by 田桂森 on 2017/4/20.
 */

public interface BaseInterface {
    void toastMessage(String message);
    RotateLoading getLoading();
    void startLoading();
    void stopLoading();
    void finish();
    
}
