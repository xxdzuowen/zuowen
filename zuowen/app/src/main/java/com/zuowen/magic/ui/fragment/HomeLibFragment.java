package com.zuowen.magic.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zuowen.magic.BaseFragment;
import com.zuowen.magic.R;
import com.zuowen.magic.adapter.ArtListAdapter;
import com.zuowen.magic.bean.request.ArtListRequest;
import com.zuowen.magic.bean.response.ArtListResponse;
import com.zuowen.magic.http.BaseHttpClient;
import com.zuowen.magic.http.HttpLoadListener;
import com.zuowen.magic.http.MagicHttpClient;
import com.zuowen.magic.ui.activity.MainActivity;
import com.zuowen.magic.ui.activity.SearchActivity;
import com.zuowen.magic.utils.UrlJsonUtil;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.loadmore.LoadMoreUIHandler;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.util.LocalDisplay;

/**
 * Created by tool on 16/1/16.
 */
public class HomeLibFragment extends BaseFragment implements View.OnClickListener{

    /**
     *  列表
     */
    private ListView mListView;

    /**
     *  适配器
     */
    private ArtListAdapter mAdapter;

    /**
     *  适配器
     */
    private RelativeLayout mSearchLayout;

    private String category;


    private PtrClassicFrameLayout mPtrFrameLayout;

    private LoadMoreListViewContainer loadMoreListViewContainer;

    private int page;


    public static HomeLibFragment newInstantFragment(int cate){
        Bundle args=new Bundle();
        args.putString(MainActivity.PAGER_MODEL,String.valueOf(cate));
        HomeLibFragment fragment=new HomeLibFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View setView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_lib,null);
    }

    @Override
    public void initView() {

        mSearchLayout=findView(R.id.fragment_lib_search_layout);


        mAdapter=new ArtListAdapter();
        mPtrFrameLayout = findView(R.id.load_more_list_view_ptr_frame);
        mPtrFrameLayout.setPullToRefresh(true);
        mPtrFrameLayout.setLoadingMinTime(1000);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });

        mListView=findView(R.id.load_more_small_image_list_view);

        loadMoreListViewContainer = findView(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultFooter();


        View headerMarginView = new View(getActivity());
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));

//        MaterialHeader headerMarginView=new MaterialHeader(getActivity());
//        headerMarginView.setLayoutParams();

        mPtrFrameLayout.setHeaderView(headerMarginView);

        mListView.setAdapter(mAdapter);


        loadMoreListViewContainer.setShowLoadingForFirstPage(true);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                sendRequest("1","1",page++);
            }
        });



        sendRequest("1","1",1);


    }

    @Override
    public void setListener() {
        findView(R.id.fragment_lib_search_text).setOnClickListener(this);
        findView(R.id.fragment_lib_search_img).setOnClickListener(this);
        findView(R.id.fragment_lib_search_layout).setOnClickListener(this);
    }

    @Override
    public void initOthers() {
        sendRequest("1","1",1);
    }


    private void sendRequest(String category,String grade,int page){

        MagicHttpClient.getInstance(getActivity()).post(UrlJsonUtil.URL + UrlJsonUtil.ZWART + UrlJsonUtil.LIST, UrlJsonUtil.getArtListJson(category, grade, page), new HttpLoadListener<ArtListResponse>() {
            @Override
            public void onSuccess(ArtListResponse model) {
                mAdapter.addData(model.data.list);
            }

            @Override
            public void onFinish() {
                super.onFinish();


            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_lib_search_text:
            case R.id.fragment_lib_search_img:
            case R.id.fragment_lib_search_layout:
                Activity activity=getActivity();
                Intent intent = new Intent();
                intent.setClass(activity, SearchActivity.class);
                startActivityForResult(intent,100);
                getActivity().overridePendingTransition(R.anim.animation_2,R.anim.animation_1);
                break;
        }
    }
}
