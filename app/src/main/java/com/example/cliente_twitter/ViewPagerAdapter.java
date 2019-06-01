package com.example.cliente_twitter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment>fragmentList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new TimelineFragment();
                break;
            case 1:
                fragment = new NewTweetFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    public CharSequence getPageTitle(int position){
        switch (position) {
            case 0:
                return "Timeline";
            case 1:
                return "New Tweet";
        }
        return null;
    }
}
