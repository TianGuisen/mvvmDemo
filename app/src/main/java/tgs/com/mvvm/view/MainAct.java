package tgs.com.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import me.yokeyword.fragmentation.SupportFragment;
import tgs.com.mvvm.R;
import tgs.com.mvvm.databinding.ActMainBinding;
import tgs.com.mvvm.utils.ToastUtil;


public class MainAct extends MySupportActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ActMainBinding binding;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_main);
        SupportFragment fragment = findFragment(MainFg.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, MainFg.newInstance());
        }
        drawer = binding.drawerLayout;
        //左侧菜单去除滚动条
        binding.navView.getChildAt(0).setVerticalScrollBarEnabled(false);
        //左侧菜单点击事件
        binding.navView.setNavigationItemSelectedListener(this);
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
    
    public void drawerSwitch(boolean show) {
        if (show) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ToastUtil.showToast(item.getTitle().toString());
        drawerSwitch(false);
        return true;
    }
}
