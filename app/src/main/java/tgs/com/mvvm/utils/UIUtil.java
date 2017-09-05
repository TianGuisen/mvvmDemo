package tgs.com.mvvm.utils;


import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

import tgs.com.mvvm.MyApplication;

public class UIUtil {
    
    /**
     * 把XML转化成View
     */
    public static View getXmlView(int layoutId) {
        return View.inflate(MyApplication.getAppContext(), layoutId, null);
    }
    
    /**
     * 获得values里的colors
     */
    public static int getColor(int colorId) {
        return ContextCompat.getColor(MyApplication.getAppContext(), colorId);
    }
    
    public static int dp2px(int dp) {
        float density = MyApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }
    
    public static int px2dp(int px) {
        float density = MyApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }
    
    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return 320
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
    
    /**
     * 获得屏幕高度
     *
     * @param context
     * @return 480
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
    
    /**
     * 定义拖拽范围
     *
     * @param activity               上下文
     * @param drawerLayout           受用的 DrawerLayout
     * @param displayWidthPercentage 拖拽范围，float
     */
    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null)
            return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField(
                    "mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField
                    .get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField(
                    "mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize,
                    (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
        }
    }
    
    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    
}
