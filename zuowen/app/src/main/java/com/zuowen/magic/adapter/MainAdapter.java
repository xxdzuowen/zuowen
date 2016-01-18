package com.zuowen.magic.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zuowen.magic.BaseFragment;
import com.zuowen.magic.ui.activity.MainActivity;
import com.zuowen.magic.ui.fragment.HomeContributeFragment;
import com.zuowen.magic.ui.fragment.HomeFindFragment;
import com.zuowen.magic.ui.fragment.HomeLibFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tool on 16/1/11.
 * 主界面栏目适配器
 */
public class MainAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    private List<Fragment> fragments;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments=new ArrayList<>();
        fragments.add(new HomeFindFragment());
        fragments.add(new HomeContributeFragment());
        fragments.add(HomeLibFragment.newInstantFragment(1));
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
//        BaseFragment fragment=new HomeFindFragment();
//        switch (position){
//            case 0:
//                fragment=new HomeFindFragment();
//                break;
//
//            case 1:
//                fragment=new HomeContributeFragment();
//                break;
//            case 2:
//                fragment=new HomeLibFragment();
//                break;
//        }
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
