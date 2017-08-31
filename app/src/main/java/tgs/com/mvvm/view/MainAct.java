package tgs.com.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.gyf.barlibrary.ImmersionBar;

import tgs.com.mvvm.R;
import tgs.com.mvvm.databinding.ActMainBinding;


public class MainAct extends MySupportActivity {
    
    private ActMainBinding binding;
    private ImmersionBar mImmersionBar;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_main);
        loadRootFragment(R.id.fl_container, MainFg.newInstance());
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //绑定Toolbar菜单
        getMenuInflater().inflate(R.menu.toolbar_menu_index, menu);
        return true;
    }
    
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mImmersionBar != null)
//            mImmersionBar.destroy();
//    }
//    
}
