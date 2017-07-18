package tgs.com.mvvm.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 田桂森 on 2017/7/18.
 */

public abstract class MyAutoLayoutActivity extends SupportActivity {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }
        
        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }
        
        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }
        
        if (view != null) return view;
        return super.onCreateView(name, context, attrs);
    }
}
