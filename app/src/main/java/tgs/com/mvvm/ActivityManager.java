package tgs.com.mvvm;

/**
 * Created by 田桂森 on 2017/5/10.
 */


import android.app.Activity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class ActivityManager {
    
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;
    
    private ActivityManager() {
    }
    
    /**
     * 单一实例
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
            activityStack = new Stack<>();
        }
        return instance;
    }
    
    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }
    
    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }
    
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }
    
    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }
    
    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    
    /**
     * 栈内activity的数量
     */
    public int getActivitySize() {
        return activityStack.size();
    }
    
    public void remove(Activity activity) {
        activityStack.remove(activity);
    }
}
