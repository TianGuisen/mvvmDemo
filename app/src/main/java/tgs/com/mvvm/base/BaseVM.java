package tgs.com.mvvm.base;

import android.databinding.BaseObservable;

import gorden.rxbus2.RxBus;


/**
 * Created by 田桂森 on 2017/4/17.
 */

public class BaseVM<I extends BaseInterface> extends BaseObservable {
    
    protected I i;
    
    public BaseVM(BaseInterface baseInterface) {
        this.i = (I) baseInterface;
    }
    
    /**
     * 初始化VM
     */
    public void init() {
        
    }
    
    /**
     * 懒加载VM
     */
    public void lazyInit() {
        
    }
    
    protected void registerRxBus() {
        RxBus.get().register(this);
    }
    
    public void onDestroy() {
        RxBus.get().unRegister(this);
    }
}
