package com.anningtex.splashtwotest.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.splashtwotest.OtherActivity;
import com.anningtex.splashtwotest.R;

/**
 * @author Administrator
 */
public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnSkip;
    private int count = 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mBtnSkip.setText("跳过 (" + getCount() + ")");
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

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
        setContentView(R.layout.activity_one);
        initView();
    }

    private void initView() {
        mBtnSkip = findViewById(R.id.btn_skip);
        mBtnSkip.setOnClickListener(this);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip:
                startActivity(new Intent(OneActivity.this, OtherActivity.class));
                handler.removeMessages(0);
                finish();
                break;
            default:
                break;
        }
    }

    public int getCount() {
        count--;
        if (count == 0) {
            startActivity(new Intent(OneActivity.this, OtherActivity.class));
            finish();
        }
        return count <= 0 ? 0 : count;
    }
}