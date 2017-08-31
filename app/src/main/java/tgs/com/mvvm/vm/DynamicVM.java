package tgs.com.mvvm.vm;

import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.IDynamic;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class DynamicVM extends BaseVM<IDynamic> {
    
    public DynamicVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
}
