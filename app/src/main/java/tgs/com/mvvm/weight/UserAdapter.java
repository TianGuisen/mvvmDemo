package tgs.com.mvvm.weight;

import java.util.List;

import tgs.com.mvvm.base.BaseAdapter;
import tgs.com.mvvm.bean.ItemInfo;
import tgs.com.mvvm.databinding.ItemTestBinding;

/**
 * Created by 田桂森 on 2017/7/18.
 * child点击事件用法,如果不需要child点击，用simpleAdapter即可。
 */

public class UserAdapter extends BaseAdapter<ItemInfo, ItemTestBinding> {
    public UserAdapter(List<ItemInfo> datas, int layoutID, int BR) {
        super(datas, layoutID, BR);
    }
    
    @Override
    protected void setExtension(ItemInfo bean, ViewHolder holder, int position) {
        ItemTestBinding binding = holder.getBinding();
        binding.tvName.setOnClickListener(v -> childClick.execute(bean, binding.tvName, position));
        binding.tvAge.setOnClickListener(v -> childClick.execute(bean, binding.tvAge, position));
    }
}
