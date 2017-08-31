package tgs.com.mvvm.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import me.yokeyword.fragmentation.ISupportFragment;
import tgs.com.mvvm.R;
import tgs.com.mvvm.core.autolayout.AutoTabLayout;
import tgs.com.mvvm.databinding.FgMainBinding;
import tgs.com.mvvm.utils.ToastUtil;
import tgs.com.mvvm.view.Iview.IMain;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.vm.MainVM;
import tgs.com.mvvm.weight.adapter.MainPagerAdapter;

/**
 * Created by 田桂森 on 2017/8/25.
 */

public class MainFg extends BaseFragment<FgMainBinding> implements IMain, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    
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
        drawer = getBind().drawerLayout;
        //左侧菜单去除滚动条
        getBind().navView.getChildAt(0).setVerticalScrollBarEnabled(false);
        //左侧菜单点击事件
        getBind().navView.setNavigationItemSelectedListener(this);
        
        //onCreateOptionsMenu执行需要这句话
        getMainAct().setSupportActionBar(getBind().toolbar);
        //去toolBar标题
        getMainAct().getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        AutoTabLayout tablayout = getBind().tablayout;
        ViewPager vpMain = getBind().vpMain;
        for (int i = 0; i < 5; i++) {
            tablayout.addTab(tablayout.newTab());
        }
        vpMain.setAdapter(new MainPagerAdapter(getChildFragmentManager()));
        tablayout.setupWithViewPager(vpMain);
        vpMain.setOffscreenPageLimit(4);
        vpMain.setCurrentItem(2);
    }
    
    @Override
    public boolean onBackPressedSupport() {
        //如果打开侧滑，按返回键先退出侧滑再退出项目
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawerSwitch(false);
            return true;
        } 
        return false;
    }
    
    public void drawerSwitch(boolean show) {
        if (show) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        ToastUtil.showToast(item.getTitle().toString());
        drawerSwitch(false);
        return true;
    }
    
}
