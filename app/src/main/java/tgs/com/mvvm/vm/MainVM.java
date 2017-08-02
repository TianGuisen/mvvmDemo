package tgs.com.mvvm.vm;

import android.view.View;

import tgs.com.mvvm.base.BaseInterface;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.core.ReplyCommand;
import tgs.com.mvvm.view.Iview.IMain;

/**
 * Created by 田桂森 on 2017/7/13.
 */

public class MainVM extends BaseVM<IMain> {
    
    public MainVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    public ReplyCommand<View> drawerSwitchClick = new ReplyCommand<View>(v -> {
        i.drawerSwitch(true);
    });
}
