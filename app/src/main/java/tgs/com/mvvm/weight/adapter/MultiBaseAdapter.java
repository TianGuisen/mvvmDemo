package tgs.com.mvvm.weight.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by 田桂森 on 2017/4/14.
 * 多种type的adapter
 */

public abstract class MultiBaseAdapter extends BaseAdapter<Object, ViewDataBinding> {
    /**
     * key:viewType,value:xml
     */
    protected ArrayMap<Integer, Integer> mItemTypeLayoutMap = new ArrayMap<>();
    /**
     * 每个position对应一个viewType
     */
    protected ArrayList<Integer> mResLayout;
    
    MultiBaseAdapter(Map<Integer, Integer> itemTypeLayoutMap) {
        mDatas = new ArrayList<>();
        mResLayout = new ArrayList<>();
        if (itemTypeLayoutMap != null && !itemTypeLayoutMap.isEmpty()) {
            mItemTypeLayoutMap.putAll(itemTypeLayoutMap);
        }
    }
    
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mItemTypeLayoutMap.get(viewType), parent, false);
        return new BindViewHolder(binding.getRoot(), binding);
    }
    
    public int getItemViewType(int position) {
        return mResLayout.get(position);
    }
    
    public void addViewTypeLayoutMap(Integer viewType, Integer layoutRes) {
        mItemTypeLayoutMap.put(viewType, layoutRes);
    }
    
    public void set(List<Object> datas, int viewType) {
        this.mDatas.clear();
        mResLayout.clear();
        if (datas == null) {
            add(null, viewType);
        } else {
            addAll(datas, viewType);
        }
    }
    
    public void add(Object data, int viewType) {
        mDatas.add(data);
        mResLayout.add(viewType);
        notifyItemInserted(0);
    }
    
    public void add(int position, Object data, int viewType) {
        mDatas.add(position, data);
        mResLayout.add(position, viewType);
        notifyItemInserted(position);
    }
    
    public void addAll(List datas, int viewType) {
        this.mDatas.addAll(datas);
        for (int i = 0; i < datas.size(); ++i) {
            mResLayout.add(viewType);
        }
        notifyDataSetChanged();
    }
    
    public void addAll(int position, List datas, int viewType) {
        this.mDatas.addAll(position, datas);
        for (int i = 0; i < datas.size(); i++) {
            mResLayout.add(position + i, viewType);
        }
        notifyItemRangeChanged(position, datas.size() - position);
    }
    
    
    public void remove(int position) {
        mResLayout.remove(position);
        super.remove(position);
    }
    
    public void clear() {
        mResLayout.clear();
        super.clear();
    }
}
