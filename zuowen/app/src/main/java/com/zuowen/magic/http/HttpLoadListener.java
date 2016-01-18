package com.zuowen.magic.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zuowen.magic.R;
import com.zuowen.magic.utils.MagicToast;

/**
 * Created by wodediannao on 15/12/24.
 */
public abstract class HttpLoadListener <T>{

    public abstract void onSuccess(T model);

    public void onFailure(Request request,final String msg) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(!TextUtils.isEmpty(msg)){
                    MagicToast.makeText(msg, Toast.LENGTH_SHORT);
                    return;
                }
                MagicToast.makeText(R.string.connect_server_fail, Toast.LENGTH_SHORT);
            }
        });



    }

    public void onStart() {

    }


    public void onFinish() {

    }

}
