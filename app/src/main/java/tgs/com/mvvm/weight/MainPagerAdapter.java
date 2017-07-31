package tgs.com.mvvm.weight;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 田桂森 on 2017/7/31.
 */

public class  MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"Re测试", "直播", "推荐", "分区", "动态"};
    
    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    
    @Override
    public int getCount() {
        return fragments.size();
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}