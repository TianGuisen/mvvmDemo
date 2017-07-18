package tgs.com.mvvm.weight;


import android.databinding.ViewDataBinding;

import java.util.List;

import tgs.com.mvvm.base.BaseAdapter;

/**
 * Created by 田桂森 on 2017/4/14.
 * 简单的adapter
 */
public class CommonAdapter<E> extends BaseAdapter<E, ViewDataBinding> {
    public CommonAdapter(List datas, int layoutID, int BR) {
        super(datas, layoutID, BR);
    }
}
