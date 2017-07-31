package tgs.com.mvvm.view;

import android.support.v7.widget.LinearLayoutManager;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.base.BaseFragment;
import tgs.com.mvvm.base.BaseVM;
import tgs.com.mvvm.databinding.FgRecyclerBinding;
import tgs.com.mvvm.view.Iview.IRecycler;
import tgs.com.mvvm.vm.RecyclerVM;
import tgs.com.mvvm.weight.UserAdapter;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class RecyclerFg extends BaseFragment<FgRecyclerBinding> implements IRecycler {
    
    private RecyclerVM vm;
    private UserAdapter userAdapter;
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        vm = new RecyclerVM(this);
        return vm;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_recycler;
    }
    
    @Override
    public void init() {
        getBind().rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        userAdapter = new UserAdapter(vm.list, R.layout.item, BR.itemInfo);
        getBind().rv.setAdapter(userAdapter);
        userAdapter.setItemClickCommand(vm.itemClick);
        userAdapter.setChildClickCommand(vm.childClick);
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
