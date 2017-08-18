package tgs.com.mvvm.vm;

import tgs.com.mvvm.R;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.view.Iview.ITest2;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class Test2VM extends BaseVM<ITest2> {
    public Test2VM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    public ReplyCommand<Object> itemClick = new ReplyCommand<Object>((itemInfo, view, position) -> {
        i.toastMessage("点击的是item:" + itemInfo.toString() + "  位置:" + position);
    });
    public ReplyCommand<Object> childClick = new ReplyCommand<Object>((itemInfo, view, position) -> {
        if (view.getId() == R.id.tv_name) {
            i.toastMessage("点击的是name:" + itemInfo.toString() + "  位置:" + position);
        } else if (view.getId() == R.id.tv_age) {
            i.toastMessage("点击的是age:" + itemInfo.toString() + "  位置:" + position);
        }
    });
}
