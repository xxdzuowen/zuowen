package com.zuowen.magic.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.R;
import com.zuowen.magic.adapter.HomeAdapter;
import com.zuowen.magic.adapter.MainAdapter;
import com.zuowen.magic.trd.LvMenuItem;
import com.zuowen.magic.ui.fragment.HomeContributeFragment;
import com.zuowen.magic.ui.fragment.HomeFindFragment;
import com.zuowen.magic.ui.fragment.HomeLibFragment;
import com.zuowen.magic.view.SelectMajorOrSubPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tool on 16/1/15.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    public static final String PAGER_MODEL="catagory";
    /**
     * 不能滚动切换的viewpager
     */
    private ViewPager mPagers;

    /**
     * 发现
     */
    private ImageView findImage;

    /**
     * 投稿
     */
    private ImageView contriImage;

    /**
     * 文库
     */
    private ImageView libImage;

    /**
     * 适配器
     */
    private MainAdapter adapter;

    private HomeAdapter homeAdapter;

    /**
     * 抽屉
     */
    private DrawerLayout mDrawerLayout;

    /**
     * 抽屉菜单
     */
    private ListView mListView;

    /**
     * 用户信息图片
     */
    private  ImageView iv_myphoto,img_sex,img_wsex;

    /**
     * 用户信息文字
     */
    private TextView maidou,grade,penname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findImage=findView(R.id.activity_main_find);
        libImage=findView(R.id.activity_main_lib);
        contriImage=findView(R.id.activity_main_contri);
        mPagers=findView(R.id.activity_main_viewpager);


        adapter=new MainAdapter(getSupportFragmentManager());
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new HomeFindFragment());
        fragments.add(new HomeContributeFragment());
        fragments.add(HomeLibFragment.newInstantFragment(1));

        homeAdapter=new HomeAdapter(getSupportFragmentManager(),fragments,mPagers);


        mPagers.setAdapter(adapter);



        findImage.setOnClickListener(this);
        libImage.setOnClickListener(this);
        contriImage.setOnClickListener(this);
        mPagers.setCurrentItem(0);
        findImage.setColorFilter(getResources().getColor(R.color.tab_selectecolor));
        /**
         * 抽屉初始化
         */
        mDrawerLayout = findView(R.id.activity_main_drawerLayout);
        mListView=findView(R.id.activity_main_leftmenu);
        setUpDrawer();
    }


    @Override
    public void onClick(View v) {
        findImage.clearColorFilter();
        libImage.clearColorFilter();
        contriImage.clearColorFilter();
        switch (v.getId()){
            case R.id.activity_main_find:
                findImage.setColorFilter(getResources().getColor(R.color.tab_selectecolor));
                mPagers.setCurrentItem(0);
                break;
            case R.id.activity_main_contri:
                contriImage.setColorFilter(getResources().getColor(R.color.tab_selectecolor));
                if (!(PreferenceManager.getDefaultSharedPreferences(this)
                        .getBoolean("login_state", false))) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    mPagers.setCurrentItem(1);
                }



                break;
            case R.id.activity_main_lib:
                libImage.setColorFilter(getResources().getColor(R.color.tab_selectecolor));
                mPagers.setCurrentItem(2);
                break;
        }
    }



    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view=  inflater.inflate(R.layout.nav_header, mListView, false);
        mListView.addHeaderView(view);
        //个人信息
        iv_myphoto=(ImageView) view.findViewById(R.id.iv_myphoto);
        penname=(TextView) view.findViewById(R.id.penname);
        grade=(TextView) view.findViewById(R.id.grade);
        maidou=(TextView) view.findViewById(R.id.maidou);
        img_sex=(ImageView) view.findViewById(R.id.img_sex);
        img_wsex=(ImageView) view.findViewById(R.id.img_wsex);




        mListView.setAdapter(new MenuItemAdapter(this));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                mDrawerLayout.closeDrawers();
            }
        });
    }

    public static class MenuItemAdapter extends BaseAdapter {
        private final int mIconSize;
        private LayoutInflater mInflater;
        private Context mContext;

        private List<LvMenuItem> mItems = new ArrayList<>(
                Arrays.asList(
                        new LvMenuItem(R.mipmap.my_composition, "我的作文"),
                        new LvMenuItem(R.mipmap.my_collection, "我的收藏"),
                        new LvMenuItem(R.mipmap.my_draft, "我的草稿"),
                        new LvMenuItem(),
                        new LvMenuItem("更 多"),
                        new LvMenuItem(R.mipmap.login_author, "退出当前账号"),
                        new LvMenuItem(R.mipmap.ic_setting, "设置")
                ));


        public MenuItemAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mContext = context;

            mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);//24dp
        }

        @Override
        public int getCount() {
            return mItems.size();
        }


        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            return mItems.get(position).type;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LvMenuItem item = mItems.get(position);
            switch (item.type) {
                case LvMenuItem.TYPE_NORMAL:
                    if (convertView == null) {
                        convertView = mInflater.inflate(R.layout.design_drawer_item, parent,
                                false);
                    }
                    TextView itemView = (TextView) convertView;
                    itemView.setText(item.name);
                    Drawable icon = mContext.getResources().getDrawable(item.icon);

                    if (icon != null) {
                        icon.setBounds(0, 0, mIconSize, mIconSize);
                        TextViewCompat.setCompoundDrawablesRelative(itemView, icon, null, null, null);
                    }

                    break;
                case LvMenuItem.TYPE_NO_ICON:
                    if (convertView == null) {
                        convertView = mInflater.inflate(R.layout.design_drawer_item_subheader,
                                parent, false);
                    }
                    TextView subHeader = (TextView) convertView;
                    subHeader.setText(item.name);
                    break;
                case LvMenuItem.TYPE_SEPARATOR:
                    if (convertView == null) {
                        convertView = mInflater.inflate(R.layout.design_drawer_item_separator,
                                parent, false);
                    }
                    break;
            }



            convertView.setPressed(true);
            convertView.setSelected(true);

            return convertView;
        }
    }

    SelectMajorOrSubPopupWindow selectMajorOrSubPopupWindow;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mPagers.getCurrentItem() == 2) {
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                selectMajorOrSubPopupWindow= new SelectMajorOrSubPopupWindow(this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.popupwindow_selectmajororsub_cancle:
                                selectMajorOrSubPopupWindow.dismiss();
                                break;
                            case R.id.popupwindow_selectmajororsub_commit:
                                break;
                        }
                    }

                });
                selectMajorOrSubPopupWindow.showAtLocation(findViewById(R.id.activity_main_tab), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }

}
