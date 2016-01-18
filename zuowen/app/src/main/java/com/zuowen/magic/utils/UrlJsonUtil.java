package com.zuowen.magic.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.zuowen.magic.bean.request.ArtListRequest;
import com.zuowen.magic.bean.request.MagicContributeRequset;
import com.zuowen.magic.bean.request.MagicLoginRequest;

/**
 * Created by worm on 2015/12/22.
 */
public class UrlJsonUtil {
    /**
     * url数据
     */
    public static final String URL="http://app.zuowen.com";
    public static final String ZWART="/zwart";
    public static final String ZWUSER="/zwuser";
    public static final String LIST="/list";
    public static final String DETAIL="/detail";
    public static final String LOGIN="/login";
    public static final String ADDARTPAGE="/addartpage";




    private static Gson mGson = new Gson();


    /**
     * 获取文章列表
     */
    public static String getArtListJson(String category,String grade,int page) {
        ArtListRequest request=new ArtListRequest();
        request.category=category;
        request.grade=grade;
        request.page=page;
        String json=mGson.toJson(request);
        Log.v("UrlJsonUtil",json);
        return json;
    }
    public  static String getContributeJson(String str1, MagicContributeRequset request) {

        request.auth=str1;

        String json = mGson.toJson(request);
        return json;
    }


    public  static String getLoginJson(String str1, String str2,String str3,MagicLoginRequest request) {
        try{
            request.setUserName(str1);
            request.setPassword(str2);
            request.setFrom(str3);
            request.setSign(MD5Encoder.encode(str1));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            String json = mGson.toJson(request);
            return json;
        }
    }

//    public  static String getDetailJson(String str1, MagicDetailRequest request) {
//
//        request.setId(str1);
//
//        String json = mGson.toJson(request);
//        return json;
//    }
//
//    public  static String getLoginJson(String str1, String str2,String str3,MagicLoginRequest request) {
//        try{
//            request.setUserName(str1);
//            request.setPassword(str2);
//            request.setFrom(str3);
//            request.setSign(MD5Encoder.encode(str1));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            String json = mGson.toJson(request);
//            System.out.println("222222222222222222222222222"+json);
//            return json;
//        }
//    }
//    public  static String getCheckJson(String str1, MagicCheckRequest request) {
//
//        request.setAuth(str1);
//
//        String json = mGson.toJson(request);
//        return json;
//    }
}
