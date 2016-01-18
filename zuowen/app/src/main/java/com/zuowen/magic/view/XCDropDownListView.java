package com.zuowen.magic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zuowen.magic.R;

import java.util.ArrayList;

/**
 * Created by worm on 2015/10/8.
 */
public class XCDropDownListView extends LinearLayout {
    private TextView editText;
    public PopupWindow popupWindow = null;
    private ArrayList<String> dataList = new ArrayList<>();
    private View mView;


    public XCDropDownListView(Context context) {
        super(context);
    }

    public XCDropDownListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public void initView() {
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getContext().getSystemService(infServie);
        View view = layoutInflater.inflate(R.layout.dropdownlist_view, this, true);
        editText = (TextView) findViewById(R.id.text);
        this.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (popupWindow == null) {
                    showPopWindow();
                } else {
                    closePopWindow();
                }
            }
        });
    }

    /**
     * 打开下拉列表弹窗
     */
    private void showPopWindow() {
        // 加载popupWindow的布局文件
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getContext().getSystemService(infServie);
        View contentView = layoutInflater.inflate(R.layout.dropdownlist_popupwindow, null, false);
        ListView mListView = (ListView) contentView.findViewById(R.id.listView);
        mListView.setAdapter(new XCDropDownListAdapter(getContext(), dataList));

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();

        popupWindow = new PopupWindow(contentView, width/2, LayoutParams.WRAP_CONTENT);
//            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.transparent));

        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(this);
    }

    /**
     * 关闭下拉列表弹窗
     */
    public void closePopWindow() {
        popupWindow.dismiss();
        popupWindow = null;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setItemsData(ArrayList<String> list,String hit) {
        dataList = list;
        editText.setText(hit);
    }

    class XCDropDownListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<String> mData;
        LayoutInflater inflater;

        public XCDropDownListAdapter(Context ctx, ArrayList<String> data) {
            mContext = ctx;
            mData = data;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            // 自定义视图
            ListItemView listItemView = null;
            if (convertView == null) {
                // 获取list_item布局文件的视图
                convertView = inflater.inflate(R.layout.xcd_recyclerview_item, null);

                listItemView = new ListItemView();
                // 获取控件对象
                listItemView.tv = (TextView) convertView
                        .findViewById(R.id.id_num);

                listItemView.layout = (LinearLayout) convertView.findViewById(R.id.layout_container);
                // 设置控件集到convertView
                convertView.setTag(listItemView);
            } else {
                listItemView = (ListItemView) convertView.getTag();
            }

            // 设置数据
            listItemView.tv.setText(mData.get(position).toString());
            final String text = mData.get(position).toString();
            listItemView.layout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    editText.setText(text);
                    closePopWindow();
                }
            });
            return convertView;
        }

    }

    private static class ListItemView {
        TextView tv;
        LinearLayout layout;
    }

}


