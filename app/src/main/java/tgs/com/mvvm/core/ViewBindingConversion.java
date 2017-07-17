package tgs.com.mvvm.core;

import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

/**
 * Created by 田桂森 on 2017/5/17.
 */

public class ViewBindingConversion {
    /**
     * 将字符串颜色值转化为ColorDrawable
     *
     * @param colorString 如：#ff0000
     * @return
     */
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(String colorString) {
        int color = Color.parseColor(colorString);
        return new ColorDrawable(color);
    }
    
    @BindingConversion
    public static int convertBooleanToInt(boolean bl) {
        return bl ? View.VISIBLE : View.INVISIBLE;
    }
    
    
    //不知道为什么下面3个方法是无效的。也就是说无法在xml里写非string类型的数据。
    @BindingConversion
    public static String convertFloatToString(float f) {
        return f + "";
    }
    
    @BindingConversion
    public static String convertDoubleToString(double d) {
        return d + "";
    }
    
    @BindingConversion
    public static String convertIntToString(int i) {
        return i + "";
    }
}
