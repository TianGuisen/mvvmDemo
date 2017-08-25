package tgs.com.mvvm.view;

import android.os.Bundle;

import tgs.com.mvvm.R;
import tgs.com.mvvm.databinding.FgVideoDetailsBinding;
import tgs.com.mvvm.view.Iview.IVideoDetails;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.VideoDetailsVM;

/**
 * Created by 田桂森 on 2017/8/24.
 */

public class VideoDetailsFg extends BaseFragment<FgVideoDetailsBinding> implements IVideoDetails {
    
    private static final String ARG_PARAM = "arg_param";
    private static final String ARG_COVER = "arg_cover";
    private VideoDetailsVM vm;
    
    public static VideoDetailsFg newInstance(String param, String cover) {
        VideoDetailsFg fragment = new VideoDetailsFg();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, param);
        bundle.putString(ARG_COVER, cover);
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
        if (bundle != null) {
            vm.param = bundle.getString(ARG_PARAM);
            vm.cover = bundle.getString(ARG_COVER);
        }
        
    }
}
