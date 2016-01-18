package com.zuowen.magic.http;

import com.squareup.okhttp.OkHttpClient;
import com.zuowen.magic.MagicApplication;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by wodediannao on 15/12/24.
 */
public class MagicClient {

    public static OkHttpClient client;
    public static PersistentCookieStore cookieStore;
    private static final int REQUEST_TIMEOUT = 10 * 1000;
    static {
        client=new OkHttpClient();
        client.setConnectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        cookieStore=new PersistentCookieStore(MagicApplication.getApplication());
        client.setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
    }
}
