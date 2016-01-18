package com.zuowen.magic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zuowen.magic.MagicApplication;

/**
 * Created by tool on 16/1/11.
 */
public abstract class BaseFragment extends Fragment{

    /* 布局View */
    private View mLayoutView = null;
    /*意图管理*/
    private Intent mIntent = null;
    /*BaseApplication实例*/
    public MagicApplication mApp;
    /*gson实例*/
    public Gson gson;



    /**
     * 系统回调方法，fragment初始化时调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 系统回调方法，fragment的view初始化
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mApp = (MagicApplication) getActivity().getApplication();
        gson = mApp.getGson();

        mLayoutView = setView();
        initView();
        initOthers();
        setListener();
        return mLayoutView;
    }


    /**
     * @bref 绑定视图
     */
    public abstract View setView();

    /**
     * @bref 初始化View
     */
    public abstract void initView();

    /**
     * @bref 设置监听器
     */
    public abstract void setListener();

    /**
     * @bref 初始化其他工具
     */
    public abstract void initOthers();



    /**
     * @bref findViewById
     */
    public <T extends View> T findView(int id) {
        return (T) mLayoutView.findViewById(id);
    }

    public void intentTo(Class<?> cls, Bundle bundle) {
        mIntent = new Intent(getActivity(), cls);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        startActivity(mIntent);

    }

    public void intentTo(Class<?> cls, Bundle bundle, int code) {
        mIntent = new Intent(getActivity(), cls);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        startActivityForResult(mIntent, code);
    }



    /**
     * 吐司
     */
    public void toast(String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(getActivity(),content, Toast.LENGTH_SHORT);
        }
    }
}
