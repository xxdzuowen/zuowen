package com.zuowen.magic.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by tool on 16/1/16.
 */
public class Helper {

    /**
     * 设置软键盘的显示与隐藏
     * @param content 可以触发软键盘的View
     * @param visible
     * @param delay 延迟时间
     */
    public static void setSoftInputVisible(final View content, boolean visible, int delay) {
        final InputMethodManager imm = (InputMethodManager)
                content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if(visible) {
            if (delay > 0) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        imm.showSoftInput(content, 0);
                    }
                }, delay);
            } else {
                imm.showSoftInput(content, 0);
            }

        } else {
            if (delay > 0) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
                    }
                }, delay);
            } else {
                imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
            }

        }
    }
}
