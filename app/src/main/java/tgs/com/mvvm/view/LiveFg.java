package tgs.com.mvvm.view;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.bean.ItemInfo;
import tgs.com.mvvm.databinding.FgLiveBinding;
import tgs.com.mvvm.view.Iview.ILive;
import tgs.com.mvvm.vm.LiveVM;
import tgs.com.mvvm.weight.adapter.Test2Adapter;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class LiveFg extends BaseFragment<FgLiveBinding> implements ILive {
    
    private LiveVM vm;
    
    public static LiveFg newInstance() {
        Bundle args = new Bundle();
        LiveFg fragment = new LiveFg();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        vm = new LiveVM(this);
        return vm;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_live;
    }
    
    @Override
    protected void init() {
        getBind().rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        Map<Integer, Integer> map = new ArrayMap<>();
        map.put(0,R.layout.item_type0);
        map.put(1,R.layout.item_type1);
        Test2Adapter test2Adapter = new Test2Adapter(map);
        List<ItemInfo> itemInfos0=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemInfo itemInfo = new ItemInfo("a",i);
            itemInfos0.add(itemInfo);
        }
        List<ItemInfo> itemInfos1=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ItemInfo itemInfo = new ItemInfo("b",i*2);
            itemInfos1.add(itemInfo);
        }
        test2Adapter.addAll(itemInfos0,0);
        test2Adapter.addAll(itemInfos1,1);
        getBind().rv.setAdapter(test2Adapter);
        test2Adapter.setItemClickCommand(vm.itemClick);
        test2Adapter.setChildClickCommand(vm.childClick);
    }
}
