package tgs.com.mvvm.vm;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import tgs.com.mvvm.core.Reply;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.IMain;

/**
 * Created by 田桂森 on 2017/7/13.
 */

public class MainVM extends BaseVM<IMain> implements NavigationView.OnNavigationItemSelectedListener {
    
    public MainVM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    public Reply<View> drawerSwitchClick = new Reply<>(v -> {
        i.drawerSwitch(true);
    });
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        i.toastMessage(item.getTitle().toString());
        i.drawerSwitch(false);
        return true;
    }
}
