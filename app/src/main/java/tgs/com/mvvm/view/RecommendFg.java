package tgs.com.mvvm.view;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;

import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;
import tgs.com.mvvm.R;
import tgs.com.mvvm.databinding.FgRecommendBinding;
import tgs.com.mvvm.utils.UIUtil;
import tgs.com.mvvm.view.Iview.IRecommend;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.RecommendVM;
import tgs.com.mvvm.weight.SpacesItemDecoration;
import tgs.com.mvvm.weight.adapter.RecommendAdapter;

import static tgs.com.mvvm.weight.adapter.RecommendAdapter.TYPE_BANNER;
import static tgs.com.mvvm.weight.adapter.RecommendAdapter.TYPE_LONG_CARD;
import static tgs.com.mvvm.weight.adapter.RecommendAdapter.TYPE_SECTION_FOOTER;
import static tgs.com.mvvm.weight.adapter.RecommendAdapter.TYPE_SECTION_HEADER;
import static tgs.com.mvvm.weight.adapter.RecommendAdapter.TYPE_STANDARD_CARD;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class RecommendFg extends BaseFragment<FgRecommendBinding> implements IRecommend {
    
    private RecommendVM vm;
    private RecommendAdapter recommendAdapter;
    
    public static RecommendFg newInstance() {
        Bundle args = new Bundle();
        RecommendFg fragment = new RecommendFg();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected BaseVM setVM() {
        vm = new RecommendVM(this);
        return vm;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_recommend;
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
        recommendAdapter = new RecommendAdapter(map);
        //设置轮播图图片
        recommendAdapter.setBannerImg(vm.listBannerImgUrl);
        //添加item type对应的list
        recommendAdapter.add(TYPE_BANNER, vm.listBanner);
        for (int i = 0, j = 0, k = 4; i < 16; i++) {
            recommendAdapter.add(TYPE_SECTION_HEADER, vm.listRecommendedHead.get(i));
            for (; j < k; j++) {
                if (i == 11) {
                    recommendAdapter.add(TYPE_LONG_CARD, vm.listRecommended.get(j));
                } else {
                    recommendAdapter.add(TYPE_STANDARD_CARD, vm.listRecommended.get(j));
                }
            }
            k = k + 4;
            recommendAdapter.add(TYPE_SECTION_FOOTER, null);
        }
        //设置点击事件
        recommendAdapter.setBannerClickListener(vm);
        recommendAdapter.setItemClickLisener(vm);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return recommendAdapter.getSpanSize(position);
            }
        };
        spanSizeLookup.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        getBind().rv.setLayoutManager(gridLayoutManager);
        //添加分割线
        getBind().rv.addItemDecoration(new SpacesItemDecoration(UIUtil.dp2px(10), spanSizeLookup));
        
        getBind().rv.setAdapter(recommendAdapter);
        
    }
    
    @Override
    public void refreshComplete(boolean refresh, boolean success) {
        if (refresh) {
            getBind().srl.finishRefresh(success);
        } else {
            getBind().srl.finishLoadmore(success);
        }
        notifyAdapter();
    }
    
    @Override
    public void startVideoDetailsFg(String param, String cover) {
        ((SupportFragment) getParentFragment()).start(VideoDetailsFg.newInstance(param,cover));
    }
    
    @Override
    public void notifyAdapter() {
        if (recommendAdapter == null) {
            initAdapter();
        } else {
            recommendAdapter.notifyDataSetChanged();
        }
    }
    
    
}
