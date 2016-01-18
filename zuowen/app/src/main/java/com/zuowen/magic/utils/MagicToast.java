package com.zuowen.magic.utils;

import android.content.Context;
import android.widget.Toast;

import com.zuowen.magic.MagicApplication;

/**
 * Created by wodediannao on 15/12/24.
 */
public class MagicToast {

    private  static Toast mToast;

    public static void makeText(String text, int duration) {

        Context def = MagicApplication.getApplication().getApplicationContext();
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(def, text, duration);
        }
        mToast.show();
    }


    public static void makeText(int resId, int duration) {
        Context def = MagicApplication.getApplication().getApplicationContext();
        makeText(def.getResources().getString(resId), duration);
    }

}
