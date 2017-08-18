package tgs.com.mvvm.weight.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tgs.com.mvvm.view.DynamicFg;
import tgs.com.mvvm.view.LiveFg;
import tgs.com.mvvm.view.Test2Fg;
import tgs.com.mvvm.view.TestFg;
import tgs.com.mvvm.view.ZoneFg;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"Re测试", "多Tpye的Re测试", "直播", "分区", "动态"};
    
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TestFg.newInstance();
            case 1:
                return Test2Fg.newInstance();
            case 2:
                return LiveFg.newInstance();
            case 3:
                return ZoneFg.newInstance();
            case 4:
                return DynamicFg.newInstance();
        }
        return null;
    }
    
    @Override
    public int getCount() {
        return titles.length;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}