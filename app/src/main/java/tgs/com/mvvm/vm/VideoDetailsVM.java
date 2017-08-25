package tgs.com.mvvm.vm;

import com.apkfuns.logutils.LogUtils;

import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.IVideoDetails;

/**
 * Created by 田桂森 on 2017/8/24.
 */

public class VideoDetailsVM extends BaseVM<IVideoDetails>{
    public String param;
    public String cover;
    
    public VideoDetailsVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    @Override
    public void init() {
        LogUtils.d(param);
    }
}
