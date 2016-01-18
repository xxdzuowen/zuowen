package com.zuowen.magic;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.zuowen.magic.MagicApplication;

import java.util.List;


/**
 * Created by worm on 2015/10/16.
 */
public class BaseActivity extends AppCompatActivity {

    private MagicApplication mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = MagicApplication.getApplication();

    }

    /**
     * 判断应用是否进入后台 网上抄的
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /**
     * 系统回调方法，activity进入后台时调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (!isAppOnForeground()) {
            // 应用进入后台
            mApp.OnAppInBackground();
        }
    }
    /**
     * 系统回调方法，activity运行时调用
     */
    @Override
    protected void onResume() {
        super.onResume();
        mApp.OnAppInForeground();

    }


    /**
     * 初始化view
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * @bref 界面跳转
     */
    public void intentTo(Activity a, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(a, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 吐司
     */
    public void toast(String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(this,content,Toast.LENGTH_SHORT);
        }
    }

}
