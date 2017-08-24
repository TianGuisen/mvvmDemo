package tgs.com.mvvm.utils;

import android.widget.Toast;

import tgs.com.mvvm.MyApplication;

/**
 * 防止重复的toast
 */

public class ToastUtil {
    private static Toast toast = null;
    
    /**
     * 显示Toast
     */
    public static Toast showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
        return toast;
    }
}
