package tgs.com.mvvm;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.autolayout.config.AutoLayoutConifg;

import me.yokeyword.fragmentation.Fragmentation;


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
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .install();
    }
    
    public static Context getAppContext() {
        return context;
    }
    
}