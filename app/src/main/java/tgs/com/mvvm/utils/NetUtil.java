package tgs.com.mvvm.utils;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import tgs.com.mvvm.MyApplication;

/**
 * 跟网络相关的工具类
 */
public class NetUtil {
  /**
   * 判断网络是否连接
   *
   * @return
   */
  public static boolean isConnected() {
    
    ConnectivityManager connectivity = (ConnectivityManager) MyApplication.getAppContext()
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    
    if (null != connectivity) {
      NetworkInfo info = connectivity.getActiveNetworkInfo();
      if (null != info && info.isConnected()) {
        if (info.getState() == NetworkInfo.State.CONNECTED) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * 打开网络设置界面
   */
  public static void openSetting(Activity activity) {
    Intent intent = new Intent("/");
    ComponentName cm = new ComponentName("com.android.settings",
        "com.android.settings.WirelessSettings");
    intent.setComponent(cm);
    intent.setAction("android.intent.action.VIEW");
    activity.startActivityForResult(intent, 0);
  }
  
}  