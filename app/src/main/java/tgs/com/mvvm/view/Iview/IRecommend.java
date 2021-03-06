package tgs.com.mvvm.view.Iview;

import tgs.com.mvvm.bean.RecommendInfo;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public interface IRecommend extends BaseInterface {
    
    void initAdapter();
    
    void notifyAdapter();
    
    /**
     * @param refresh true刷新完毕，flase加载完毕
     * @param success true成功，flase失败
     */
    void refreshComplete(boolean refresh, boolean success);
    
    void startVideoDetailsFg(RecommendInfo.ResultBean.BodyBean bodyBean);
    
}
