package com.zuowen.magic.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.R;
import com.zuowen.magic.adapter.ArtListAdapter;
import com.zuowen.magic.bean.response.ArtListResponse;
import com.zuowen.magic.http.HttpLoadListener;
import com.zuowen.magic.http.MagicHttpClient;
import com.zuowen.magic.utils.UrlJsonUtil;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.util.LocalDisplay;

/**
 * Created by tool on 16/1/16.
 */
public class SearchActivity extends BaseActivity{

    /**
     *  列表
     */
    private ListView mListView;

    /**
     *  适配器
     */
    private ArtListAdapter mAdapter;

    int page;
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private PtrFrameLayout mPtrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mAdapter=new ArtListAdapter();


        mPtrFrameLayout = findView(R.id.load_more_list_view_ptr_frame);
        mPtrFrameLayout.setPullToRefresh(true);
        mPtrFrameLayout.setLoadingMinTime(1000);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return false;
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
//
//
        loadMoreListViewContainer = findView(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultFooter();

//        mListView.setAdapter(mAdapter);
//
//        View headerMarginView = new View(this);
//        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));


        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString("");


        mPtrFrameLayout.setHeaderView(header);
//

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

    private void sendRequest(String category,String grade,int page){

        MagicHttpClient.getInstance(this).post(UrlJsonUtil.URL + UrlJsonUtil.ZWART + UrlJsonUtil.LIST, UrlJsonUtil.getArtListJson(category, grade, page), new HttpLoadListener<ArtListResponse>() {
            @Override
            public void onSuccess(ArtListResponse model) {
                mAdapter.addData(model.data.list);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadMoreListViewContainer.loadMoreFinish(false,true);
                mPtrFrameLayout.autoRefresh();
            }
        });
    }
}
