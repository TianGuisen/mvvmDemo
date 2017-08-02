package tgs.com.mvvm.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseActivity;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.core.autolayout.AutoTabLayout;
import tgs.com.mvvm.databinding.ActMainBinding;
import tgs.com.mvvm.view.Iview.IMain;
import tgs.com.mvvm.vm.MainVM;
import tgs.com.mvvm.weight.MainPagerAdapter;


public class MainAct extends BaseActivity<ActMainBinding> implements IMain {
    
    private MainVM mainVM;
    private List<Fragment> fragments;
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
        fragments = new ArrayList<>();
        fragments.add(new RecyclerFg());
        fragments.add(new LiveFg());
        fragments.add(new RecommendFg());
        fragments.add(new ZoneFg());
        fragments.add(new DynamicFg());
        for (int i = 0; i < fragments.size(); i++) {
            tablayout.addTab(tablayout.newTab());
        }
        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
        tablayout.setupWithViewPager(vpMain);
        
        //菜单去除滚动条
        getBind().navView.getChildAt(0).setVerticalScrollBarEnabled(false);
        //菜单点击事件
        getBind().navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerSwitch(false);
                return true;
            }
        });
        //
        setSupportActionBar(getBind().toolbar);
        //去toolBar标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //绑定Toolbar菜单
        getMenuInflater().inflate(R.menu.toolbar_menu_index, menu);
        return true;
    }
    
    @Override
    public void onBackPressed() {
        //如果打开侧滑，按返回键先退出侧滑再退出项目
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawerSwitch(false);
        } else {
            super.onBackPressed();
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
