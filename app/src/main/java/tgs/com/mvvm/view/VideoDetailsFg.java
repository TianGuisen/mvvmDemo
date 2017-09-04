package tgs.com.mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import tgs.com.mvvm.R;
import tgs.com.mvvm.bean.RecommendInfo;
import tgs.com.mvvm.core.autolayout.AutoTabLayout;
import tgs.com.mvvm.databinding.FgVideoDetailsBinding;
import tgs.com.mvvm.utils.GlideUtil;
import tgs.com.mvvm.view.Iview.IVideoDetails;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.VideoDetailsVM;
import tgs.com.mvvm.weight.adapter.DetailsPagerAdapter;

/**
 * Created by 田桂森 on 2017/8/24.
 */

public class VideoDetailsFg extends BaseFragment<FgVideoDetailsBinding> implements IVideoDetails {
    
    private static final String ARG_PARAM = "arg_param";
    private VideoDetailsVM vm;
    
    public static VideoDetailsFg newInstance(RecommendInfo.ResultBean.BodyBean bodyBean) {
        VideoDetailsFg fragment = new VideoDetailsFg();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM, bodyBean);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    protected BaseVM setVM() {
        vm = new VideoDetailsVM(this);
        return vm;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_video_details;
    }
    
    @Override
    protected void init() {
        Bundle bundle = getArguments();
        vm.bodyBean = (RecommendInfo.ResultBean.BodyBean) bundle.getSerializable(ARG_PARAM);
        
        GlideUtil.setImg(getBind().iv, vm.bodyBean.getCover());
        
        AutoTabLayout tablayout = getBind().tablayout;
        ViewPager vpDetails = getBind().vpDetails;
        tablayout.addTab(tablayout.newTab());
        tablayout.addTab(tablayout.newTab());
        vpDetails.setAdapter(new DetailsPagerAdapter(getChildFragmentManager()));
        tablayout.setupWithViewPager(vpDetails);
        getBind().appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                
                if (verticalOffset == 0) {
                    
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    getBind().btbar.setVisibility(View.VISIBLE);//隐藏播放按钮
                } else {
                    getBind().btbar.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                }
            }
        });
    }
    
    @Override
    public void appBarState(boolean b) {
        getBind().appbar.setExpanded(b);
        if (b) {
            Observable.just("")
                    .subscribeOn(Schedulers.io())
                    .subscribe(s -> {
                        Thread.sleep(700);
                        starPlayer();
                    });
        }
    }
    
    @Override
    public void starPlayer() {
        Intent intent=new Intent(getActivity(),IjkPlayerActivity.class);
        intent.putExtra(IjkPlayerActivity.VIDEO_CID,vm.videoDetailsInfo.getPages().get(0).getCid());
        intent.putExtra(IjkPlayerActivity.VIDEO_TITLE,vm.videoDetailsInfo.getTitle());
        startActivity(intent);
    }
}
