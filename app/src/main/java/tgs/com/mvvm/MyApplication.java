package tgs.com.mvvm;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhy.autolayout.config.AutoLayoutConifg;


public class MyApplication extends Application {
    private static Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();//适配设置为整个屏幕而不是忽略顶部状态栏
        context = this;
        Fresco.initialize(this);
        //设置log不带边框。tag为“tian”
        LogUtils.getLogConfig().configAllowLog(true).configShowBorders(false).configTagPrefix("tian");
        
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context1, layout) -> {
            //指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context1).setSpinnerStyle(SpinnerStyle.Translate);
        });
        
    }
    
    public static Context getAppContext() {
        return context;
    }
    
}