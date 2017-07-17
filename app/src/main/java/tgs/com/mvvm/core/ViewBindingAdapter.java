package tgs.com.mvvm.core;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by 田桂森 on 2017/4/14.
 * databinding核心
 */
public final class ViewBindingAdapter {
    /**
     * 点击监听
     */
    @BindingAdapter({"click"})
    public static void clickCommand(View view, final ReplyCommand clickCommand) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCommand != null) {
                    clickCommand.execute();
                }
            }
        });
    }
    
    /**
     * 焦点控制
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }
    
    /**
     * 可见控制
     */
    @BindingAdapter({"setVisible"})
    public static void setVisible(View view, final Boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
    
    /**
     * 焦点改变监听
     */
    @BindingAdapter({"focusChange"})
    public static void onFocusChangeCommand(View view, final ReplyCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }
    
    /**
     * onTouch监听
     */
    @BindingAdapter({"touch"})
    public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (onTouchCommand != null) {
                    return onTouchCommand.execute(event);
                }
                return false;
            }
        });
    }
    
    /**
     * 刷新控件,可以换成任意其他的
     */
    @BindingAdapter({"load"})
    public static void onLoadCommand(BGARefreshLayout bgaRefreshLayout, final ReplyCommand onRefreshCommand) {
        bgaRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(bgaRefreshLayout.getContext(), true));
        bgaRefreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute(0);
                }
            }
            
            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute(1);
                }
                return false;
            }
        });
    }
    
    /**
     * image加载网络图片
     */
    @BindingAdapter({"imageUrl"})
    public static void imageUri(SimpleDraweeView simpleDraweeView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            simpleDraweeView.setImageURI(Uri.parse(imageUrl));
        }
    }
    
    /**
     * image加载本地图片
     */
    @BindingAdapter({"imageLocal"})
    public static void localImage(SimpleDraweeView sdv, @IdRes int id) {
        if (id > 0) {
            //注意包名
            Uri uri = Uri.parse("res://com.smartpeak.zhwscan/" + id);
            sdv.setImageURI(uri);
        }
    }
    
    /**
     * EditText焦点控制
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            editText.setFocusableInTouchMode(true);
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        } else {
            editText.setEnabled(false);
            editText.setEnabled(true);
        }
        
    }
    
    /**
     * EditText监听内容变化
     */
    @BindingAdapter(value = {"beforeTextChanged", "textChanged", "afterTextChanged"}, requireAll = false)
    public static void editTextCommand(EditText editText,
                                       final ReplyCommand<TextChangeDataWrapper> beforeTextChangedCommand,
                                       final ReplyCommand<TextChangeDataWrapper> onTextChangedCommand,
                                       final ReplyCommand<String> afterTextChangedCommand) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (beforeTextChangedCommand != null) {
                    beforeTextChangedCommand.execute(new TextChangeDataWrapper(s, start, count, count));
                }
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onTextChangedCommand != null) {
                    onTextChangedCommand.execute(new TextChangeDataWrapper(s, start, before, count));
                }
            }
            
            @Override
            public void afterTextChanged(Editable s) {
                if (afterTextChangedCommand != null) {
                    afterTextChangedCommand.execute(s.toString());
                }
            }
        });
    }
    
    public static class TextChangeDataWrapper {
        public CharSequence s;
        public int start;
        public int before;
        public int count;
        
        public TextChangeDataWrapper(CharSequence s, int start, int before, int count) {
            this.s = s;
            this.start = start;
            this.before = before;
            this.count = count;
        }
    }
    
    /**
     * scrollView
     */
    @BindingAdapter({"scrollChange"})
    public static void onScrollChangeCommand(final NestedScrollView nestedScrollView, final ReplyCommand<NestScrollDataWrapper> onScrollChangeCommand) {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (onScrollChangeCommand != null) {
                    onScrollChangeCommand.execute(new NestScrollDataWrapper(scrollX, scrollY, oldScrollX, oldScrollY));
                }
            }
        });
    }
    
    public static class NestScrollDataWrapper {
        public int scrollX;
        public int scrollY;
        public int oldScrollX;
        public int oldScrollY;
        
        public NestScrollDataWrapper(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.oldScrollX = oldScrollX;
            this.oldScrollY = oldScrollY;
        }
    }
    
    /**
     * viewPager监听
     */
    @BindingAdapter(value = {"pageScrolled", "pageSelected", "pageScrollStateChanged"}, requireAll = false)
    public static void onScrollChangeCommand(final ViewPager viewPager,
                                             final ReplyCommand<ViewPagerDataWrapper> onPageScrolledCommand,
                                             final ReplyCommand<Integer> onPageSelectedCommand,
                                             final ReplyCommand<Integer> onPageScrollStateChangedCommand) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int state;
            
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (onPageScrolledCommand != null) {
                    onPageScrolledCommand.execute(new ViewPagerDataWrapper(position, positionOffset, positionOffsetPixels, state));
                }
            }
            
            @Override
            public void onPageSelected(int position) {
                if (onPageSelectedCommand != null) {
                    onPageSelectedCommand.execute(position);
                }
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {
                this.state = state;
                if (onPageScrollStateChangedCommand != null) {
                    onPageScrollStateChangedCommand.execute(state);
                }
            }
        });
        
    }
    
    public static class ViewPagerDataWrapper {
        public float positionOffset;
        public float position;
        public int positionOffsetPixels;
        public int state;
        
        public ViewPagerDataWrapper(float position, float positionOffset, int positionOffsetPixels, int state) {
            this.positionOffset = positionOffset;
            this.position = position;
            this.positionOffsetPixels = positionOffsetPixels;
            this.state = state;
        }
    }
    
    @BindingAdapter("checkedChange")
    public static void onRadioGroupCheckedChangeCommand(RadioGroup radioGroup,
                                                        final ReplyCommand<Integer> onCheckedCommand) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> onCheckedCommand.execute(checkedId));
    }
    
    @BindingAdapter({"render"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }
    
    
}

