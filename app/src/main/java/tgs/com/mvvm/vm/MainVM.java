package tgs.com.mvvm.vm;

import java.util.ArrayList;
import java.util.List;

import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.bean.ItemInfo;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.view.Iview.IMain;

/**
 * Created by 田桂森 on 2017/7/13.
 */

public class MainVM extends BaseVM<IMain> {
    public List<ItemInfo> list = new ArrayList<>();
    
    
    public MainVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    @Override
    public void init() {
        for (int i1 = 0; i1 < 10; i1++) {
            list.add(new ItemInfo("" + i1, i1));
        }
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
