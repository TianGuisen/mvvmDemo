package tgs.com.mvvm.weight.adapter;

import java.util.Map;

/**
 * Created by 田桂森 on 2017/8/18.
 * 多type如果不需要设置item中子类用这个
 */

public class SimpleMultiAdapter extends MultiBaseAdapter {
    
    public SimpleMultiAdapter(Map<Integer, Integer> itemTypeLayoutMap) {
        super(itemTypeLayoutMap);
    }
}
