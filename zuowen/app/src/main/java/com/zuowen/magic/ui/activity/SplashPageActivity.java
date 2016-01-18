package com.zuowen.magic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.MagicApplication;
import com.zuowen.magic.R;

import java.util.ArrayList;
import java.util.List;

public class SplashPageActivity extends BaseActivity {


    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ViewPager viewpager;
    private Button button;
    // 引导图片资源
    private static final int[] pics={
      R.mipmap.stup1,  R.mipmap.stup2,R.mipmap.stup3
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        initViews();
        views=new ArrayList<>();

        // 为引导图片提供布局参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        for (int pic : pics) {
            ImageView iv = new ImageView(this);

            iv.setLayoutParams(mParams);
            iv.setImageResource(pic);
            views.add(iv);
        }
         //初始化Adpter
        vpAdapter =new ViewPagerAdapter(views);
        viewpager.setAdapter(vpAdapter);

        //绑定回调
        viewpager.setOnPageChangeListener(new OnPageChangeListener() {

            // 当新的页面被选中时调用
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.GONE);
                }
            }
            // 当前页面被滑动时调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            // 当滑动状态改变时调用
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                    intent.setClass(SplashPageActivity.this, AreaActivity.class);

                SplashPageActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    private void initViews() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        button = (Button) findViewById(R.id.button);
    }




    public class ViewPagerAdapter extends PagerAdapter {
        //界面列表
        private List<View> views;

        public ViewPagerAdapter(List<View> views){
            this.views=views;
        }

        //删除界面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public int getCount() {
            if(views!=null){
                return  views.size();
            }
            return 0;
        }
        //初始化position的位置界面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
               container.addView(views.get(position), 0);
            return  views.get(position);
        }
        //判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==object);
        }
    }



}
