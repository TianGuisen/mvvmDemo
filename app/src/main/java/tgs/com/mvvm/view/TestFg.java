package tgs.com.mvvm.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import tgs.com.mvvm.BR;
import tgs.com.mvvm.R;
import tgs.com.mvvm.vm.BaseVM;
import tgs.com.mvvm.databinding.FgTestBinding;
import tgs.com.mvvm.view.Iview.ITest;
import tgs.com.mvvm.vm.TestVM;
import tgs.com.mvvm.weight.adapter.TestAdapter;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class TestFg extends BaseFragment<FgTestBinding> implements ITest {
    
    private TestVM vm;
    private TestAdapter testAdapter;
    
    public static TestFg newInstance() {
        Bundle args = new Bundle();
        TestFg fragment = new TestFg();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int setBR() {
        return BR.vm;
    }
    
    @Override
    protected BaseVM setVM() {
        vm = new TestVM(this);
        return vm;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fg_test;
    }
    
    @Override
    public void init() {
     
        getBind().rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        testAdapter = new TestAdapter(vm.list, R.layout.item_test);
        getBind().rv.setAdapter(testAdapter);
        testAdapter.setItemClickCommand(vm.itemClick);
        testAdapter.setChildClickCommand(vm.childClick);
    }
    
    @Override
    public void refreshComplete(boolean refresh, boolean success) {
        if (refresh) {
            getBind().srl.finishRefresh(success);
        } else {
            getBind().srl.finishLoadmore(success);
        }
        notifyAdapter();
    }
    
    @Override
    public void notifyAdapter() {
        testAdapter.notifyDataSetChanged();
    }
    
}
