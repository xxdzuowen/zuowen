package com.zuowen.magic.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.R;
import com.zuowen.magic.view.CityPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaActivity extends BaseActivity implements View.OnClickListener{

    private ImageView im_pic;
    private TextView et_area,tv_selector;
    private TextView et_selector,et_grade;
    private Button bt_start;
    private CityPicker citypicker;
    private Context mContext = null;
    private PopupWindow popupWindow;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        mContext = this;

        initViews();


    }




    private void initViews() {
        im_pic = (ImageView) findViewById(R.id.im_pic);
        citypicker=(CityPicker)findViewById(R.id.citypicker);
        et_grade = (TextView) findViewById(R.id.et_grade);
        et_selector = (TextView) findViewById(R.id.et_selector);
        bt_start = (Button) findViewById(R.id.bt_start);
        et_selector.setOnClickListener(this);
        bt_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_selector:

                showPopupWindow(v);
                break;
            case R.id.bt_start:
                Intent intent = new Intent(AreaActivity.this, LoginActivity.class);
                AreaActivity.this.startActivity(intent);
                finish();
                break;


        }
    }
    private void showPopupWindow(View view){

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.grade_pop_window, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, true);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.grade_listview_item,
                new String[] { "text" },
                new int[] { R.id.item });
        listView = (ListView) contentView.findViewById(R.id.lv_grade);
        listView.setAdapter(adapter);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.update();
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(et_selector, 0, 0);
        }

    }
    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("text", "一年级");
        list.add(map);

        map = new HashMap<>();
        map.put("text", "二年级");
        list.add(map);

        map = new HashMap<>();
        map.put("text", "三年级");
        list.add(map);

        map = new HashMap<>();
        map.put("text", "四年级");
        list.add(map);

        map = new HashMap<>();
        map.put("text", "五年级");
        list.add(map);
        map = new HashMap<>();
        map.put("text", "六年级");
        list.add(map);
        map = new HashMap<>();
        map.put("text", "初一");
        list.add(map);
        map = new HashMap<>();
        map.put("text", "初二");
        list.add(map);
        map = new HashMap<>();
        map.put("text", "初三");
        list.add(map);map = new HashMap<>();
        map.put("text", "高一");
        list.add(map);
        map = new HashMap<>();
        map.put("text", "高二");
        list.add(map);
        map = new HashMap<>();
        map.put("text", "高三");
        list.add(map);



        return list;
    }

}
