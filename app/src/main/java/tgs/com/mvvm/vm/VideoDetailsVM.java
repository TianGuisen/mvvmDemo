package tgs.com.mvvm.vm;

import android.databinding.ObservableField;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

import tgs.com.mvvm.bean.RecommendInfo;
import tgs.com.mvvm.bean.VideoDetailsInfo;
import tgs.com.mvvm.core.Reply;
import tgs.com.mvvm.core.retrofit.MyObserver;
import tgs.com.mvvm.core.retrofit.RetrofitUtil;
import tgs.com.mvvm.core.retrofit.RxHelper;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.IVideoDetails;

/**
 * Created by 田桂森 on 2017/8/24.
 */

public class VideoDetailsVM extends BaseVM<IVideoDetails> {
    public RecommendInfo.ResultBean.BodyBean bodyBean;
    public VideoDetailsInfo videoDetailsInfo;
    public VideoDetailsVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    @Override
    public void init() {
        //            i.startVideoDetailsFg(bodyBean.getParam(),bodyBean.getCover(),bodyBean.getCover());
        RetrofitUtil.getJson().getVideoDetails(bodyBean.getParam())
                .compose(RxHelper.ioMain())
                .subscribe(new MyObserver<VideoDetailsInfo>(true, null) {
                    @Override
                    protected void next(VideoDetailsInfo videoDetailsInfo1) {
                        videoDetailsInfo=videoDetailsInfo1;
                    }
                });
    }
    
    public Reply<View> barPlay = new Reply<>(view -> {
        i.appBarState(true);
    });
    
    public Reply<View> actionPlay = new Reply<>(view -> {
        i.starPlayer();
    });
}
