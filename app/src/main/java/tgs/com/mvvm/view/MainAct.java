package tgs.com.mvvm.view;

import android.support.v7.widget.LinearLayoutManager;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseActivity;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.ActMainBinding;
import tgs.com.mvvm.view.Iview.IMain;
import tgs.com.mvvm.vm.MainVM;
import tgs.com.mvvm.weight.UserAdapter;


public class MainAct extends BaseActivity<ActMainBinding> implements IMain {
    
    private MainVM mainVM;
    private UserAdapter userAdapter;
    
    @Override
    protected int setBR() {
        return BR.mainVM;
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
        getBind().rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        userAdapter = new UserAdapter(mainVM.list, R.layout.item, BR.itemInfo);
        getBind().rv.setAdapter(userAdapter);
        userAdapter.setItemClickCommand(mainVM.itemClick);
        userAdapter.setChildClickCommand(mainVM.childClick);
    }
    
    @Override
    public void refreshComplete(boolean refresh,boolean success) {
        if (refresh) {
            getBind().srl.finishRefresh(success);
        } else {
            getBind().srl.finishLoadmore(success);
        }
        notifyAdapter();
    }
    
    @Override
    public void notifyAdapter() {
        userAdapter.notifyDataSetChanged();
    }
}
