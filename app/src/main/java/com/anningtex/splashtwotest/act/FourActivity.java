package com.anningtex.splashtwotest.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.anningtex.splashtwotest.OtherActivity;
import com.anningtex.splashtwotest.R;
import com.anningtex.splashtwotest.constant.AppConstants;
import com.anningtex.splashtwotest.four.WelcomeGuideActivity;
import com.anningtex.splashtwotest.utils.SpUtils;

/**
 * @author Administrator
 * desc:功能引导页
 */
public class FourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_four);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 4000);
    }

    private void enterHomeActivity() {
        startActivity(new Intent(this, OtherActivity.class));
        finish();
    }
}