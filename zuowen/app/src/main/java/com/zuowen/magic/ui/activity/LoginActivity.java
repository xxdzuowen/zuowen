package com.zuowen.magic.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.R;
import com.zuowen.magic.bean.request.MagicLoginRequest;
import com.zuowen.magic.bean.response.LoginReponse;
import com.zuowen.magic.http.BaseHttpClient;
import com.zuowen.magic.http.HttpLoadListener;
import com.zuowen.magic.utils.UrlJsonUtil;
import com.zuowen.magic.view.CircularImage;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private long mLastClickTime = 0;
    private CircularImage cover_user_photo;
    private EditText user_name;
    private LinearLayout input;
    private EditText user_pwd;
    private Button btnlogin;
    private TextView forget_psw;
    private TextView regist_account;

    private TextView cacel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        loginMain();



    }

    private void initViews() {
        cover_user_photo = (CircularImage) findViewById(R.id.cover_user_photo);
        user_name = (EditText) findViewById(R.id.user_name);
        input = (LinearLayout) findViewById(R.id.input);
        user_pwd = (EditText) findViewById(R.id.user_pwd);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        forget_psw = (TextView) findViewById(R.id.forget_psw);
        regist_account = (TextView) findViewById(R.id.regist_account);

        findView(R.id.activity_login_cacel).setOnClickListener(this);


        cover_user_photo.setImageResource(R.mipmap.login_author);
    }


    private void loginMain() {
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickTime();
                //TODO  网络链接判断
                if (TextUtils.isEmpty(user_name.getText().toString())) {
                    user_name.setHint("请输入账号");
                    user_name.setHintTextColor(getResources().getColor(
                            R.color.login_red));
                    return;
                }
                if (TextUtils.isEmpty(user_pwd.getText().toString())) {
                    user_pwd.setHint("请输入密码");
                    user_pwd.setHintTextColor(getResources().getColor(
                            R.color.login_red));
                    return;
                }
                final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                //TODO 实际应该去发送账号密码去服务端根据返回的数据判断
                new BaseHttpClient().post(UrlJsonUtil.URL+UrlJsonUtil.ZWUSER+UrlJsonUtil.LOGIN,
                        UrlJsonUtil.getLoginJson(user_name.getText().toString(), user_pwd.getText().toString()
                                , "app", new MagicLoginRequest()), new HttpLoadListener<LoginReponse>() {
                            @Override
                            public void onStart() {
                                super.onStart();
                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Loading");
                                pDialog.setCancelable(false);
                                pDialog.show();
                            }

                            @Override
                    public void onSuccess(LoginReponse model) {
                                pDialog.dismiss();
                        if ("200".equals(model.code)) {
                            //TODO
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit()
                                    .putString("auth", model.data.auth).commit();
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit()
                                    .putBoolean("login_state", true).commit();
                            finish();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else if("400".equals(model.code)){
                            Toast.makeText(LoginActivity.this, model.msg+"!", Toast.LENGTH_SHORT).show();

                        }

                    }

                        });


            }

        });
        forget_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTime();

            }
        });

        regist_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTime();
            }
        });


    }
    public void clickTime(){
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_cacel:

                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
