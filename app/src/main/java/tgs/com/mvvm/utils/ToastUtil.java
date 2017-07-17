package tgs.com.mvvm.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 防止重复的toast
 */

public class ToastUtil {
    private static Toast toast = null;
    
    /**
     * 显示Toast
     */
    public static Toast showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
        return toast;
    }
}
