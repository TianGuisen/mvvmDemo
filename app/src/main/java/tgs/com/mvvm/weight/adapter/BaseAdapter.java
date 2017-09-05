package tgs.com.mvvm.weight.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import tgs.com.mvvm.BR;

/**
 * Created by 田桂森 on 2017/8/17.
 */

public abstract class BaseAdapter<E, VB extends ViewDataBinding> extends RecyclerView.Adapter<BaseAdapter.BindViewHolder> {
    List<E> mDatas;
    onItemClickLisener<E> itemClickLisener;
    onChildClickLisener<E> childClickLisener;
    onItemLongClickLisener<E> itemLongClickLisener;
    
    BaseAdapter() {
    }
    
    BaseAdapter(List<E> datas) {
        mDatas = datas;
    }
    
    
    public void setChildClickLisener(onChildClickLisener<E> childClickLisener) {
        this.childClickLisener = childClickLisener;
    }
    
    public void setItemClickLisener(onItemClickLisener<E> itemClickLisener) {
        this.itemClickLisener = itemClickLisener;
    }
    
    public void setItemLongClickLisener(onItemLongClickLisener<E> itemLongClickLisener) {
        this.itemLongClickLisener = itemLongClickLisener;
    }
    
    public interface onItemClickLisener<E> {
        void itemClick(E bean, View view, int position);
    }
    
    public interface onChildClickLisener<E> {
        void childClick(E bean, View view, int position);
    }
    
    public interface onItemLongClickLisener<E> {
        void itemLongClick(E bean, View view, int position);
    }
  
    @Override
    public void onBindViewHolder(BaseAdapter.BindViewHolder holder, int position) {
        E bean = mDatas.get(position);
        View itemView = holder.binding.getRoot();
        if (itemClickLisener != null) {
            itemView.setOnClickListener(v -> {
                //noinspection unchecked
                itemClickLisener.itemClick(bean, holder.binding.getRoot(), position);
            });
        }
        decorator(bean, holder, position);
        holder.binding.setVariable(BR.item, mDatas.get(position));
        holder.binding.executePendingBindings();
    }
    
    public void decorator(E bean, BaseAdapter.BindViewHolder holder, int position) {
        
    }
    
    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }
    
    class BindViewHolder extends RecyclerView.ViewHolder {
        
        public VB binding;
        
        BindViewHolder(View itemView, VB binding) {
            super(itemView);
            //适配
            AutoUtils.autoSize(itemView);
            this.binding = binding;
        }
    }
}
