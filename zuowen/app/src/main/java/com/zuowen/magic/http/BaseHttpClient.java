package com.zuowen.magic.http;

import com.zuowen.magic.R;

import static com.zuowen.magic.MagicApplication.getApplication;

/**
 * Created by wodediannao on 15/12/24.
 */
public class BaseHttpClient {


    private static AbsHttpClient mClient;

    static {
        mClient= MagicHttpClient.getInstance(getApplication());
    }

    public static void post(String url,String params, HttpLoadListener responseHandler) {
        if(responseHandler!=null){
            responseHandler.onStart();
        }

        if (!checkNetWork(responseHandler)) {
            return;
        }

        mClient.post(url,params,responseHandler);


    }



    private static boolean checkNetWork(HttpLoadListener responseHandler) {
        boolean result = getApplication().isNetworkAvailable();

        if (!result && responseHandler != null) {
            responseHandler.onFailure(null, getApplication().getString(R.string.connect_server_fail));
            responseHandler.onFinish();
        }



        return result;
    }

}
