package tgs.com.mvvm.weight.adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 田桂森 on 2017/4/14.
 * 简单的adapter,不支持item中子view的点击
 */
abstract class SigleBaseAdapter<E, VB extends ViewDataBinding> extends BaseAdapter<E, VB> {
    
    private int layoutId;
    
    private SigleBaseAdapter(List<E> datas) {
        super(datas);
    }
    
    SigleBaseAdapter(List<E> datas, int layoutId) {
        super(datas);
        this.layoutId = layoutId;
    }
    
    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VB binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        return new BindViewHolder(binding.getRoot(), binding);
    }
    
    public void add(E data) {
        mDatas.add(data);
        notifyDataSetChanged();
    }
    
    public void add(int position, E data) {
        mDatas.add(position, data);
        notifyDataSetChanged();
    }
    
    public void set(List<E> data) {
        mDatas.clear();
        addAll(data);
    }
    
    public void addAll(List<E> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }
}
