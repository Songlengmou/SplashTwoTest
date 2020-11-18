package com.anningtex.splashtwotest.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.splashtwotest.OtherActivity;
import com.anningtex.splashtwotest.R;
import com.anningtex.splashtwotest.three.Shimmer;
import com.anningtex.splashtwotest.three.ShimmerTextView;

/**
 * @author Administrator
 */
public class ThreeActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mRlSplash;
    private ShimmerTextView mShimmerTv;
    private TextView mTvNumber;
    private Shimmer shimmer;

    AlphaAnimation animation;
    MyCount my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_three);
        initView();
        countDown();
    }

    private void initView() {
        mShimmerTv = findViewById(R.id.shimmer_tv);
        mTvNumber = findViewById(R.id.tv_number);
        mTvNumber.setOnClickListener(this);
        mRlSplash = findViewById(R.id.rl_splash);

        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(mShimmerTv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_number:
                startActivity(new Intent(ThreeActivity.this, OtherActivity.class));
                my.cancel();
                finish();
                break;
            default:
                break;
        }
    }

    private void countDown() {
        animation = new AlphaAnimation(0.5f, 1.0f);
        animation.setDuration(3000);
        my = new MyCount(4000, 1000);
        my.start();
        mRlSplash.setAnimation(animation);
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvNumber.setText("跳过" + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            startActivity(new Intent(ThreeActivity.this, OtherActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}