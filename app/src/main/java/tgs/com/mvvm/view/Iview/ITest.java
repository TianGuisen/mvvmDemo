package tgs.com.mvvm.view.Iview;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public interface ITest extends BaseInterface {
    /**
     * @param refresh true刷新完毕，flase加载完毕
     * @param success true成功，flase失败
     */
    void refreshComplete(boolean refresh, boolean success);
    
    void notifyAdapter();
}
