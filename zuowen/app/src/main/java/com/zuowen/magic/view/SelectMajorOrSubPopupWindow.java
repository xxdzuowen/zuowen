package com.zuowen.magic.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.zuowen.magic.R;


/**
 * Created by tool on 16/1/5.
 */
public class SelectMajorOrSubPopupWindow extends PopupWindow {

    private Context mContext;

    private View mMenuView;




    private int subjectIndex;



    public SelectMajorOrSubPopupWindow(Context context, View.OnClickListener listener){
        super(context);
        this.mContext=context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.contribute_popwindow, null);



//        mMenuView.findViewById(R.id.popupwindow_selectmajororsub_bglayout).getBackground().setAlpha(153);




        mMenuView.findViewById(R.id.popupwindow_selectmajororsub_cancle).setOnClickListener(listener);
        mMenuView.findViewById(R.id.popupwindow_selectmajororsub_commit).setOnClickListener(listener);
        mMenuView.findViewById(R.id.popupwindow_selectmajororsub_edit).setOnClickListener(listener);


        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.select_pic_dialog_style);
        // 实例化一个ColorDrawable颜色为半透明
        // ColorDrawable dw = new ColorDrawable(0xb0000000);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(android.R.color.transparent));
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        mMenuView.setOnTouchListener(new View.OnTouchListener()
//        {
//
//            public boolean onTouch(View v, MotionEvent event)
//            {
//
//                int height = mMenuView.findViewById(R.id.popupwindow_selectmajororsub_contentlayout).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP)
//                {
//                    if (y < height)
//                    {
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });

    }







}
