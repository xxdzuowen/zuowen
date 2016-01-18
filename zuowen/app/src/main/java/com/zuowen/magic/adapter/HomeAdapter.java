package com.zuowen.magic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tool on 16/1/16.
 */
public class HomeAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private ViewPager viewPager; // viewPager对象
    private int currentPageIndex = 0; // 当前page索引（切换之前）
    public HomeAdapter(FragmentManager fm,List<Fragment> fragments,ViewPager viewPager) {
        this.fragments = fragments;
        this.fragmentManager = fm;
        this.viewPager = viewPager;
        this.viewPager.setAdapter(this);
        this.viewPager.setOnPageChangeListener(this);
    }


    @Override
    public int getCount() {
        if(fragments==null){
            return 0;
        }
        return fragments.size();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(fragments.get(position).getView());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragments.get(position);
        if(!fragment.isAdded()){ // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        if(fragment.getView().getParent() == null){
            container.addView(fragment.getView()); // 为viewpager增加布局
        }
        return fragment.getView();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        fragments.get(currentPageIndex).onPause();
        if(fragments.get(position).isAdded()){
            fragments.get(position).onResume();
        }
        currentPageIndex = position;


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}