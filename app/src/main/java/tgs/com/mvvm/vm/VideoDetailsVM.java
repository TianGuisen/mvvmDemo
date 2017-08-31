package tgs.com.mvvm.vm;

import android.databinding.ObservableField;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

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
    public String param;
    public String cover;
    
    public VideoDetailsVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    @Override
    public void init() {
        RetrofitUtil.getJson().getVideoDetails(param)
                .compose(RxHelper.ioMain())
                .subscribe(new MyObserver<VideoDetailsInfo>(true, null) {
                    @Override
                    protected void next(VideoDetailsInfo videoDetailsInfo) {
                        LogUtils.d(videoDetailsInfo);
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
