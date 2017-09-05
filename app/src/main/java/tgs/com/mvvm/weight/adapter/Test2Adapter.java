package tgs.com.mvvm.weight.adapter;

import java.util.Map;

import tgs.com.mvvm.databinding.ItemType0Binding;
import tgs.com.mvvm.databinding.ItemType1Binding;

/**
 * Created by 田桂森 on 2017/8/17.
 * 多type，child点击事件用法,如果不需要child点击，用SimpleMultiAdapter即可。
 */

public class Test2Adapter extends MultiBaseAdapter {
    
    public Test2Adapter(Map<Integer, Integer> itemTypeLayoutMap) {
        super(itemTypeLayoutMap);
    }
    
    @Override
    public void decorator(Object bean, BaseAdapter.BindViewHolder holder, int position) {
        Integer integer = mResLayout.get(position);
        switch (integer) {
            case 0:
                ItemType0Binding binding0 = (ItemType0Binding) holder.binding;
                binding0.tvName.setOnClickListener(v -> childClickLisener.childClick(bean,binding0.tvName,position));
                binding0.tvAge.setOnClickListener(v -> childClickLisener.childClick(bean,binding0.tvAge,position));
                break;
            case 1:
                ItemType1Binding binding1 = (ItemType1Binding) holder.binding;
                binding1.tvName.setOnClickListener(v ->childClickLisener.childClick(bean,binding1.tvName,position));
                binding1.tvAge.setOnClickListener(v ->childClickLisener.childClick(bean,binding1.tvAge,position));
                break;
        }
    }
}
