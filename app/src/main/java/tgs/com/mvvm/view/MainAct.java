package tgs.com.mvvm.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
        fragments = new ArrayList<>();
        fragments.add(new RecyclerFg());
        fragments.add(new LiveFg());
        fragments.add(new RecommendFg());
        fragments.add(new ZoneFg());
        fragments.add(new DynamicFg());
        
        AutoTabLayout tablayout = getBind().tablayout;
        ViewPager vpMain = getBind().vpMain;
        for (int i = 0; i < fragments.size(); i++) {
            tablayout.addTab(tablayout.newTab());
        }
        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
        tablayout.setupWithViewPager(vpMain);
        getBind().navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //绑定Toolbar菜单
        getMenuInflater().inflate(R.menu.toolbar_menu_index, menu);
        return true;
    }
    
}
