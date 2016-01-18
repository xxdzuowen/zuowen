package com.zuowen.magic.http;

import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

/**
 * Created by wodediannao on 15/12/24.
 */
public abstract class AbsHttpClient {

    /**
     * cookie
     */
    public abstract void clearCookies();

    public abstract List<HttpCookie> getCookie();

    public abstract void setCookies(URI uri,HttpCookie cookie);

    /**
     * 代理
     * @param host
     * @param port
     */
    public abstract void setProxy(String host, int port);

    public abstract void removeProxy();


    /**
     * header
     * @param header
     * @param value
     */
    public abstract void addHttpHeader(String header, String value);

    public abstract void post(String url,String params,HttpLoadListener responseHanlder);
}
