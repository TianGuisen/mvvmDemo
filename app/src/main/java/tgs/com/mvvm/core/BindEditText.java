package tgs.com.mvvm.core;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by 田桂森 on 2017/7/27.
 * 实现了使用ObservableBoolean监听和控制edittext的焦点。
 * bind:focus="@={vm.?}"
 */

public class BindEditText extends android.support.v7.widget.AppCompatEditText {
    
    public BindEditText(Context context) {
        super(context);
    }
    
    public BindEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    
    public BindEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (onFocusChangedListener != null) {
            onFocusChangedListener.onChanged();
        }
    }
    
    public void setListener(OnFocusChangedListener onFocusChangedListener) {
        this.onFocusChangedListener = onFocusChangedListener;
    }
    
    private OnFocusChangedListener onFocusChangedListener;
    
    public interface OnFocusChangedListener {
        void onChanged();
    }
    
    @BindingAdapter(value = "focus")
    public static void setFocus(BindEditText view, boolean focus) {
        if (focus) {
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }
    
    @InverseBindingAdapter(attribute = "focus", event = "focusAttrChanged")
    public static boolean isFocus(BindEditText view) {
        return view.hasFocus();
    }
    
    @BindingAdapter(value = "focusAttrChanged")
    public static void setChangeListener(BindEditText aView, final InverseBindingListener focusAttrChange) {
        aView.setListener(focusAttrChange::onChange);
    }
    
    
    
}