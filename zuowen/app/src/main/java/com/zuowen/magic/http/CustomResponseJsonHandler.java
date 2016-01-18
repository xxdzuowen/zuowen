package com.zuowen.magic.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wodediannao on 15/12/24.
 *
 */
public class CustomResponseJsonHandler<T> implements Callback {




    private HttpLoadListener<T> listener;
    private Type genericType;
    private Handler mHandler;
    public CustomResponseJsonHandler(HttpLoadListener<T> listener) {
        this.listener = listener;
        mHandler=new Handler(Looper.getMainLooper());
        if (listener != null) {
            Type superType = listener.getClass().getGenericSuperclass();
            genericType = ((ParameterizedType) superType).getActualTypeArguments()[0];
        }
    }




    @Override
    public void onFailure(final Request request, final IOException e) {
        if(mHandler!=null){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onFailure(request,e.getMessage());
                    listener.onFinish();
                }
            });

        }


    }

    @Override
    public void onResponse(Response response) throws IOException {
        final String resp=response.body().string();
        if(mHandler!=null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        T typedClass = null;
                        try {
                            typedClass = new GsonBuilder().create().fromJson(resp, genericType);

                            listener.onSuccess(typedClass);


                        } catch (Exception ex) {
                            ex.printStackTrace();
                            listener.onFinish();
                        }


                        listener.onFinish();
                    }
                }

            });
        }

    }


}
