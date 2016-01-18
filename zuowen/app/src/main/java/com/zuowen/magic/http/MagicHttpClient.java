package com.zuowen.magic.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

/**
 * Created by wodediannao on 15/12/24.
 */
public class MagicHttpClient extends AbsHttpClient {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client=MagicClient.client;
    private static PersistentCookieStore cookieStore=MagicClient.cookieStore;

    private static MagicHttpClient mInstance;
    private Request request;

    public static MagicHttpClient getInstance(Context context){
        if(mInstance==null){
            synchronized (MagicHttpClient.class){
                if(mInstance==null){
                    mInstance=new MagicHttpClient();
                }
            }
        }
        return mInstance;
    }



    @Override
    public void clearCookies() {
        cookieStore.removeAll();
        client.setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
    }

    @Override
    public List<HttpCookie> getCookie() {
        return cookieStore.getCookies();
    }

    @Override
    public void setCookies(URI uri,HttpCookie cookie) {
        cookieStore.add(uri,cookie);
        client.setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
    }

    @Override
    public void setProxy(String host, int port) {

    }

    @Override
    public void removeProxy() {

    }

    @Override
    public void addHttpHeader(String header, String value) {

    }

    @Override
    public void post( String url, String params, HttpLoadListener responseHanlder) {
        RequestBody body = RequestBody.create(JSON, params);


        request=new Request.Builder().url(url).post(body).build();
        

        CustomResponseJsonHandler handler=new CustomResponseJsonHandler(responseHanlder);

        client.newCall(request).enqueue(handler);
    }
}
