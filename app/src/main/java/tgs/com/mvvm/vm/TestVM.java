package tgs.com.mvvm.vm;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.bean.ItemInfo;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.view.Iview.ITest;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class TestVM extends BaseVM<ITest> {
    public List<ItemInfo> list = new ArrayList<>();
    
    
    public TestVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    public final ObservableField<String> urlImg = new ObservableField("https://avatars6.githubusercontent.com/u/19662707?v=4&s=40");
    public final ObservableField<String> editText = new ObservableField("没有焦点");
    public final ObservableInt localImg = new ObservableInt(R.drawable.ic_nav_star);
    public final ObservableBoolean focus = new ObservableBoolean();
    
    @Override
    public void lazyInit() {
        for (int i1 = 0; i1 < 10; i1++) {
            list.add(new ItemInfo("" + i1, i1));
        }
        i.notifyAdapter();
        focus.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (focus.get()) {
                    editText.set("获取焦点了");
                } else {
                    editText.set("丢失焦点了");
                }
            }
        });
    }
    
    public ReplyCommand<ItemInfo> itemClick = new ReplyCommand<>((itemInfo, view, position) -> {
        i.toastMessage("点击的是item:" + itemInfo.toString() + "  位置:" + position);
    });
    
    public ReplyCommand<ItemInfo> childClick = new ReplyCommand<>((itemInfo, view, position) -> {
        if (view.getId() == R.id.tv_name) {
            i.toastMessage("点击的是name:" + itemInfo.toString() + "  位置:" + position);
        } else if (view.getId() == R.id.tv_age) {
            i.toastMessage("点击的是age:" + itemInfo.toString() + "  位置:" + position);
        }
    });
    
    public final ReplyCommand<View> imgClick = new ReplyCommand<>(v -> {
        localImg.set(R.drawable.test_img1);
    });
    
    public final ReplyCommand<View> focusClick = new ReplyCommand<>(v -> {
        focus.set(true);
    });
    
    public ReplyCommand loadData = new ReplyCommand<Integer>(i -> {
        if (i == 0) {
            //刷新
            list.clear();
            for (int i1 = 0; i1 < 10; i1++) {
                list.add(new ItemInfo("刷新的数据" + i1, i1));
            }
            super.i.refreshComplete(true, true);
        } else {
            //加载更多
            for (int i1 = 0; i1 < 10; i1++) {
                list.add(new ItemInfo("加载更多的数据" + i1, i1));
            }
            super.i.refreshComplete(false, true);
        }
    });
    
}