package tgs.com.mvvm.vm;

import android.view.View;

import tgs.com.mvvm.R;
import tgs.com.mvvm.view.Iview.BaseInterface;
import tgs.com.mvvm.view.Iview.ITest2;
import tgs.com.mvvm.weight.adapter.BaseAdapter;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class Test2VM extends BaseVM<ITest2> implements BaseAdapter.onItemClickLisener<Object>, BaseAdapter.onChildClickLisener<Object> {
    public Test2VM(BaseInterface baseInterface) {
        super(baseInterface);
    }
    
    @Override
    public void itemClick(Object bean, View view, int position) {
        i.toastMessage("点击的是item:" + bean.toString() + "  位置:" + position);
    }
    
    @Override
    public void childClick(Object bean, View view, int position) {
        if (view.getId() == R.id.tv_name) {
            i.toastMessage("点击的是name:" + bean.toString() + "  位置:" + position);
        } else if (view.getId() == R.id.tv_age) {
            i.toastMessage("点击的是age:" + bean.toString() + "  位置:" + position);
        }
    }
}
