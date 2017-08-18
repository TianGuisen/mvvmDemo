package tgs.com.mvvm.weight.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.core.ReplyCommand;

/**
 * Created by 田桂森 on 2017/8/17.
 */

abstract class BaseAdapter<E, VB extends ViewDataBinding> extends RecyclerView.Adapter<BaseAdapter.BindViewHolder> {
    List<E> mDatas;
    
    BaseAdapter(List<E> datas) {
        mDatas = datas;
    }
    
    private ReplyCommand<E> itemClick;
    ReplyCommand<E> childClick;
    
    protected BaseAdapter() {
    }
    
    public void setItemClickCommand(ReplyCommand<E> itemClick) {
        this.itemClick = itemClick;
    }
    
    public void setChildClickCommand(ReplyCommand<E> childClick) {
        this.childClick = childClick;
    }
    
    @Override
    public void onBindViewHolder(BaseAdapter.BindViewHolder holder, int position) {
        E bean = mDatas.get(position);
        View itemView = holder.binding.getRoot();
        if (itemClick != null) {
            itemView.setOnClickListener(v -> {
                itemClick.execute(bean, holder.binding.getRoot(), position);
            });
        }
        decorator(bean,holder, position);
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
