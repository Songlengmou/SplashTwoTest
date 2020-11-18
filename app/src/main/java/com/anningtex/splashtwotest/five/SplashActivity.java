package com.anningtex.splashtwotest.five;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

import com.anningtex.splashtwotest.R;

import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //创建一个文件用来储存app的开启次数状态
        SharedPreferences jame = getSharedPreferences("jame", 0);
        //这个文件里面的布尔常量名，和它的初始状态，状态为是，则触发下面的方法
        boolean isFirst = jame.getBoolean("isFirst", true);
        if (isFirst) {
            //显示引导页界面
            setContentView(R.layout.activity_splash);
            //创建状态储存文件
            SharedPreferences.Editor edit = jame.edit();
            //将参数put，改变其状态
            edit.putBoolean("isFirst", false);
            //保证文件的创建和编辑
            edit.commit();
            //这个方法是一个计时器
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    //下面的功能实现了引导页的逐渐关闭
                    int VERSION = Integer.parseInt(android.os.Build.VERSION.SDK);
                    if (VERSION >= 5) {
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                    finish();
                }
            }.start();
        } else {
            //否则就显示注册界面
            setContentView(R.layout.activity_login);
        }
    }
}