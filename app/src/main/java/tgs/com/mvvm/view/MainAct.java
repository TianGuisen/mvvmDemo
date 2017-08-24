package tgs.com.mvvm.view;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.core.autolayout.AutoTabLayout;
import tgs.com.mvvm.databinding.ActMainBinding;
import tgs.com.mvvm.view.Iview.IMain;
import tgs.com.mvvm.vm.MainVM;
import tgs.com.mvvm.weight.adapter.MainPagerAdapter;


public class MainAct extends BaseActivity<ActMainBinding> implements IMain {
    
    private MainVM mainVM;
    private DrawerLayout drawer;
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        mainVM = new MainVM(this);
        return mainVM;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.act_main;
    }
    
    @Override
    public void init() {
        AutoTabLayout tablayout = getBind().tablayout;
        ViewPager vpMain = getBind().vpMain;
        drawer = getBind().drawerLayout;
        for (int i = 0; i < 5; i++) {
            tablayout.addTab(tablayout.newTab());
        }
        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(vpMain);
        
        //左侧菜单去除滚动条
        getBind().navView.getChildAt(0).setVerticalScrollBarEnabled(false);
        //左侧菜单点击事件
        getBind().navView.setNavigationItemSelectedListener(mainVM);
        //onCreateOptionsMenu执行需要这句话
        setSupportActionBar(getBind().toolbar);
        //去toolBar标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        vpMain.setOffscreenPageLimit(4);
        vpMain.setCurrentItem(2);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //绑定Toolbar菜单
        getMenuInflater().inflate(R.menu.toolbar_menu_index, menu);
        return true;
    }
    
    @Override
    public void onBackPressedSupport() {
        //如果打开侧滑，按返回键先退出侧滑再退出项目
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawerSwitch(false);
        } else {
            super.onBackPressedSupport();
        }
    }
    
    @Override
    public void drawerSwitch(boolean show) {
        if (show) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}
