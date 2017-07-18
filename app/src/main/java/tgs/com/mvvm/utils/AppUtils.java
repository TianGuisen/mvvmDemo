package tgs.com.mvvm.utils;


import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

import tgs.com.mvvm.MyApplication;

public class AppUtils {
    
    
    private static PackageManager packageManager;
    
    public static PackageManager getPackageManager() {
        if (packageManager == null) {
            packageManager = MyApplication.getAppContext().getPackageManager();
        }
        return packageManager;
    }
    
    /**
     * 返回应用的VersionName
     *
     * @return
     */
    public static String getVersionName() {
        // 1.获取包管理器     2. 通过管理器获取包对象
        String versionName = null;
        try {
            PackageInfo info = getPackageManager().getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
    
    /**
     * 返回应用的VersionCode
     *
     * @return
     */
    public static int getVersionCode() {
        
        int versionCode = 0;
        try {
            PackageInfo info = getPackageManager().getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
    
    public static String getAppProcessName() {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) MyApplication.getAppContext().getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
    
    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
    
}
