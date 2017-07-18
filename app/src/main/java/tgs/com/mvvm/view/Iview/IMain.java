package tgs.com.mvvm.view.Iview;

import tgs.com.mvvm.base.BaseInterface;

/**
 * Created by 田桂森 on 2017/7/13.
 */

public interface IMain extends BaseInterface {
    /**
     * @param refresh true刷新完毕，flase加载完毕
     * @param success true成功，flase失败
     */
    void refreshComplete(boolean refresh, boolean success);
    
    void notifyAdapter();
}
