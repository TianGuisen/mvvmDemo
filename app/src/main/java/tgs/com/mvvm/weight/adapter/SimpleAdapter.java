package tgs.com.mvvm.weight.adapter;

import android.databinding.ViewDataBinding;

import java.util.List;

/**
 * Created by 田桂森 on 2017/8/17.
 * 如果不需要设置item中子类用这个
 */

public class SimpleAdapter<E> extends SigleBaseAdapter<E,ViewDataBinding> {
    public SimpleAdapter(List datas, int layoutId) {
        super(datas, layoutId);
    }
    
}
