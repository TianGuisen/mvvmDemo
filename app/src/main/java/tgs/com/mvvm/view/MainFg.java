package tgs.com.mvvm.view;

import android.support.v4.view.ViewPager;

import me.yokeyword.fragmentation.ISupportFragment;
import tgs.com.mvvm.R;
import tgs.com.mvvm.core.autolayout.AutoTabLayout;
import tgs.com.mvvm.databinding.FgMainBinding;
import tgs.com.mvvm.view.Iview.IMain;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.MainVM;
import tgs.com.mvvm.weight.adapter.MainPagerAdapter;

/**
 * Created by 田桂森 on 2017/8/25.
 */

public class MainFg extends BaseFragment<FgMainBinding> implements IMain {
    
    @Override
    protected BaseVM setVM() {
        return new MainVM(this);
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_main;
    }
    
    public static ISupportFragment newInstance() {
        return new MainFg();
    }
    
    @Override
    protected void init() {
        AutoTabLayout tablayout = getBind().tablayout;
        ViewPager vpMain = getBind().vpMain;
        for (int i = 0; i < 5; i++) {
            tablayout.addTab(tablayout.newTab());
        }
        vpMain.setAdapter(new MainPagerAdapter(getChildFragmentManager()));
        tablayout.setupWithViewPager(vpMain);
        //onCreateOptionsMenu执行需要这句话
        getMainAct().setSupportActionBar(getBind().toolbar);
        //去toolBar标题
        getMainAct().getSupportActionBar().setDisplayShowTitleEnabled(false);
        vpMain.setOffscreenPageLimit(4);
        vpMain.setCurrentItem(2);
    }
    
    @Override
    public void drawerSwitch(boolean show) {
        
    }
}
