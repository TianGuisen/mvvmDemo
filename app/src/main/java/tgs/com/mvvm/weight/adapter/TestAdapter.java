package tgs.com.mvvm.weight.adapter;

import java.util.List;

import tgs.com.mvvm.bean.ItemInfo;
import tgs.com.mvvm.databinding.ItemTestBinding;

/**
 * Created by 田桂森 on 2017/7/18.
 * child点击事件用法,如果不需要child点击，用simpleAdapter即可。
 */

public class TestAdapter extends SigleBaseAdapter<ItemInfo, ItemTestBinding> {

    public TestAdapter(List<ItemInfo> datas, int layoutId) {
        super(datas, layoutId);
    }

    @Override
    public void decorator(ItemInfo bean, BaseAdapter.BindViewHolder holder, int position) {
        ItemTestBinding binding = (ItemTestBinding) holder.binding;
        binding.tvName.setOnClickListener(v -> childClickLisener.childClick(bean,binding.tvName,position));
        binding.tvAge.setOnClickListener(v -> childClickLisener.childClick(bean,binding.tvAge,position));
    }
}
