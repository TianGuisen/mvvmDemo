package tgs.com.mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tgs.com.mvvm.core.ReplyCommand;


/**
 * Created by 田桂森 on 2017/4/14.
 * 基础的Adapter
 */

public abstract class BaseAdapter<E, V extends ViewDataBinding> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    protected List<E> datas;
    protected int layoutID;
    protected int BR;
    
    /**
     * list需要是ObservableList
     */
    public BaseAdapter(List<E> datas, int layoutID, int BR) {
        this.datas = datas;
        this.layoutID = layoutID;
        this.BR = BR;
    }
    
    protected ReplyCommand<E> itemClickCommand;
    protected ReplyCommand<E> childClickCommand;
    public void setItemClickCommand(ReplyCommand<E> itemClickCommand) {
        this.itemClickCommand = itemClickCommand;
    }
    
    public void setChildClickCommand(ReplyCommand<E> childClickCommand) {
        this.childClickCommand = childClickCommand;
    }
    
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        V binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutID, parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }
    
    @Override
    public void onBindViewHolder(final BaseAdapter.ViewHolder holder, final int position) {
        final E bean = datas.get(position);
        final View itemView = holder.getBinding().getRoot();
        if (itemClickCommand != null) {
            itemView.setOnClickListener(v -> {
                itemClickCommand.execute(bean, holder.getBinding().getRoot(), position);
            });
        }
        setExtension(bean,  holder, position);
        holder.getBinding().setVariable(BR, datas.get(position));
        holder.getBinding().executePendingBindings();
    }
    
    protected void setExtension(E bean, ViewHolder bind, int position) {
        
    }
    
    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        
        public V binding;
        
        public ViewHolder(View itemView) {
            super(itemView);
        }
        
        public void setBinding(V binding) {
            this.binding = binding;
        }
        
        public V getBinding() {
            return this.binding;
        }
    }
    
}
