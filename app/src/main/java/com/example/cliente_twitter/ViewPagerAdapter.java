package com.example.cliente_twitter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
