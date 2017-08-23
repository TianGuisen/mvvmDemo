package tgs.com.mvvm.view;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;

import java.util.Map;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.databinding.FgLiveBinding;
import tgs.com.mvvm.utils.UIUtil;
import tgs.com.mvvm.view.Iview.ILive;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.LiveVM;
import tgs.com.mvvm.weight.SpacesItemDecoration;
import tgs.com.mvvm.weight.adapter.LiveAdapter;

import static tgs.com.mvvm.weight.adapter.LiveAdapter.TYPE_BANNER;
import static tgs.com.mvvm.weight.adapter.LiveAdapter.TYPE_LONG_CARD;
import static tgs.com.mvvm.weight.adapter.LiveAdapter.TYPE_SECTION_FOOTER;
import static tgs.com.mvvm.weight.adapter.LiveAdapter.TYPE_SECTION_HEADER;
import static tgs.com.mvvm.weight.adapter.LiveAdapter.TYPE_STANDARD_CARD;

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
        
    }
    
    @Override
    public void initAdapter() {
        //添加item type和对应的xml
        Map<Integer, Integer> map = new ArrayMap<>();
        map.put(TYPE_BANNER, R.layout.item_banner);
        map.put(TYPE_SECTION_HEADER, R.layout.item_section_header);
        map.put(TYPE_SECTION_FOOTER, R.layout.item_section_footer);
        map.put(TYPE_STANDARD_CARD, R.layout.item_standard_card);
        map.put(TYPE_LONG_CARD, R.layout.item_long_card);
        LiveAdapter liveAdapter = new LiveAdapter(map);
        //设置轮播图图片
        liveAdapter.setBannerImg(vm.listBannerImgUrl);
        //添加item type对应的list
        liveAdapter.add(TYPE_BANNER, vm.listBanner);
        liveAdapter.add(TYPE_SECTION_HEADER, null);
        for (int i = 0; i < 4; i++) {
        liveAdapter.add(TYPE_STANDARD_CARD,vm.listRecommended.get(i));
        }
        getBind().rv.setAdapter(liveAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int spanSize = liveAdapter.getSpanSize(position);
                return liveAdapter.getSpanSize(position);
            }
        };
        spanSizeLookup.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        getBind().rv.setLayoutManager(gridLayoutManager);
        getBind().rv.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(10), spanSizeLookup));
    }
}
