package tgs.com.mvvm.weight.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tgs.com.mvvm.view.Test2Fg;
import tgs.com.mvvm.view.TestFg;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class DetailsPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"简介", "评论"};
    
    public DetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Test2Fg.newInstance();
            case 1:
                return Test2Fg.newInstance();
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