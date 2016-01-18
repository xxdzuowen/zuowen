package com.zuowen.magic.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuowen.magic.R;
import com.zuowen.magic.utils.Helper;

import org.w3c.dom.Text;

/**
 * Created by tool on 16/1/16.
 */
public class SearchView extends LinearLayout implements View.OnClickListener{

    private CustomEditTextView mContentEditText;
    private TextView mSearchBtn;

    private ImageView mClearBtn;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
        initListener();
    }

    private OnSearchBtnClick mOnSearchClick;

    private OnSearchContentViewChange mOnContentChange;

    private OnSoftInputStateChange mOnSoftInputStateChange;

    private String searchTipText;

    public SearchView(Context context) {
        super(context);
        initLayout(context);
        initListener();
    }



    private void initLayout(Context context){

        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_search_layout, this);
        mContentEditText=(CustomEditTextView) findViewById(R.id.view_search_content);
        mSearchBtn=(TextView)findViewById(R.id.view_search_btn);
        mClearBtn=(ImageView) findViewById(R.id.view_search_clear);

        mClearBtn.setOnClickListener(this);
        mSearchBtn.setOnClickListener(this);
        mClearBtn.setVisibility(GONE);
    }



    private void initListener(){
        // 添加 监听
        mContentEditText.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                // TODO Auto-generated method stub
                mClearBtn.setVisibility(VISIBLE);
                if (TextUtils.isEmpty(s.toString()))
                {
                    mClearBtn.setVisibility(INVISIBLE);
                }

                if (mOnContentChange == null)
                {
                    return;
                }
                mOnContentChange.onContentTextChange(s.toString());
            }
        });

        mContentEditText.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                // TODO Auto-generated method stub

                if (!hasFocus)
                {
                    hideSoftInput();
                }
            }
        });

        mContentEditText.setOnFinishComposingListener(new CustomEditTextView.OnFinishComposingListener()
        {

            @Override
            public void finishComposing()
            {
                // TODO 自动生成的方法存根

                if (isClearContent)
                {
                    isClearContent = false;
                    return;
                }

                if (mOnSoftInputStateChange != null)
                {
                    mOnSoftInputStateChange.onHideSoftInput();
                }
            }
        });

        this.mContentEditText.setOnTouchListener(new OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // TODO 自动生成的方法存根
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:

                        if (mOnSoftInputStateChange != null)
                        {
                            mOnSoftInputStateChange.onShowSoftInput();
                        }
                        Helper.setSoftInputVisible(mContentEditText, true, 0);
                        break;
                }
                return false;
            }
        });
    }


    public void hideSoftInput()
    {
        Helper.setSoftInputVisible(mContentEditText, false, 0);
    }

    public void showSoftInput()
    {
        mContentEditText.requestFocus();
        Helper.setSoftInputVisible(mContentEditText, true, 0);
    }
    boolean isClearContent = false;
    @Override
    public void onClick(View v) {


        switch (v.getId())
        {
            case R.id.view_search_clear:
                isClearContent = true;
                mContentEditText.setText("");
                break;
            case R.id.view_search_btn:
                doSearch();
                break;
        }

    }


    private void doSearch()
    {
        String searchWord = mContentEditText.getText().toString();

        searchWord = searchWord.trim();

        if (searchWord.contains(" "))
        {

            return;
        }
        if (mOnSearchClick == null)
        {
            return;
        }
        mOnSearchClick.onSearch(searchWord);
    }

    /**
     * 获取当前搜索词
     */
    public String getSearchContent()
    {
        return this.mContentEditText.getText().toString().trim();
    }


    /**
     * 设置当搜索按钮被点击时的 动作
     */
    public void setSearchClickListener(OnSearchBtnClick paramSearchBtnClick)
    {
        this.mOnSearchClick = paramSearchBtnClick;
    }

    /**
     * 设置当搜索框内容改变时 相应的操作
     */
    public void setContentTextChangeListener(OnSearchContentViewChange paramContentTextChange)
    {
        this.mOnContentChange = paramContentTextChange;
    }

    /**
     * 设置按钮展示文字
     */
    public void setDoSearchBtnText(String text)
    {
        this.mSearchBtn.setText(text);
    }


    /**
     * 当搜索按钮被点击时调用
     *
     * @author yanshch
     *
     */
    public interface OnSearchBtnClick
    {
        /**
         * 点击动作 执行搜索
         */
        public void onSearch(String searchWord);
    }


    /**
     * 当搜索框内容改变时调用
     */
    public interface OnSearchContentViewChange
    {
        /**
         * 搜索框内容改变 执行相应操作
         */
        public void onContentTextChange(String searchWord);
    }

    /**
     * 接口用来监听搜索框中软键盘的状态
     */
    public interface OnSoftInputStateChange
    {
        /**
         * 当软键盘弹出时调用 不能监听全部 只能监听到由触摸编辑框弹出的软键盘
         */
        public void onShowSoftInput();

        /**
         * 当软键盘隐藏时调用 不能监听全部 只能监听由编辑框关联的软键盘的隐藏
         */
        public void onHideSoftInput();
    }
}
