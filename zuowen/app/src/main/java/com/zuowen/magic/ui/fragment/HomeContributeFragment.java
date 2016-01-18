package com.zuowen.magic.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zuowen.magic.BaseFragment;
import com.zuowen.magic.R;
import com.zuowen.magic.bean.model.ContributeModel;
import com.zuowen.magic.bean.request.MagicContributeRequset;
import com.zuowen.magic.bean.response.ContributeResponse;
import com.zuowen.magic.http.BaseHttpClient;
import com.zuowen.magic.http.HttpLoadListener;
import com.zuowen.magic.http.MagicHttpClient;
import com.zuowen.magic.utils.UrlJsonUtil;
import com.zuowen.magic.view.XCDropDownListView;

import java.util.ArrayList;

/**
 * Created by tool on 16/1/11.
 */
public class HomeContributeFragment extends BaseFragment {
    private final String TAG = this.getClass().getSimpleName();
    public XCDropDownListView tv_article, tv_activity;
    private EditText edt_content,et_title;

    /**
     * 投稿按钮
     *
     */
    private TextView commit;
    @Override
    public View setView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_contribute,null);
    }

    @Override
    public void initView() {
        tv_article = findView(R.id.tv_article);
        tv_activity = findView(R.id.tv_activity);
        edt_content=findView(R.id.edt_content);
        et_title=findView(R.id.et_title);
        commit=findView(R.id.fragment_home_contribute_commit);

    }

    @Override
    public void setListener() {
    }

    @Override
    public void initOthers() {
        initNet();
    }








    public void initNet(){
        final ArrayList<String> artList = new ArrayList<>();
        final ArrayList<String> activitiesList = new ArrayList<>();
        MagicHttpClient.getInstance(getActivity()).post(UrlJsonUtil.URL + UrlJsonUtil.ZWART + UrlJsonUtil.ADDARTPAGE,
                UrlJsonUtil.getContributeJson(PreferenceManager.getDefaultSharedPreferences(getContext())
                        .getString("auth", ""), new MagicContributeRequset()), new HttpLoadListener<ContributeResponse>() {
                    @Override
                    public void onSuccess(ContributeResponse model) {
                        artList.clear();
                        activitiesList.clear();
                        for (ContributeModel title : model.data.artThemes) {
                            artList.add(title.title);
                        }
                        for (ContributeModel title : model.data.activities) {
                            activitiesList.add(title.title);
                        }

                    }
                });

        tv_article.setItemsData(artList, "请选择题材");
        tv_activity.setItemsData(activitiesList, "请选择活动");
    }


}
