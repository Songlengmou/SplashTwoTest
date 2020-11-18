package com.anningtex.splashtwotest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.splashtwotest.act.FiveActivity;
import com.anningtex.splashtwotest.act.FourActivity;
import com.anningtex.splashtwotest.act.OneActivity;
import com.anningtex.splashtwotest.act.ThreeActivity;
import com.anningtex.splashtwotest.act.TwoActivity;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnOne, mBtnTwo, mBtnThree, mBtnFour, mBtnFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnOne = findViewById(R.id.btn_one);
        mBtnOne.setOnClickListener(this);
        mBtnTwo = findViewById(R.id.btn_two);
        mBtnTwo.setOnClickListener(this);
        mBtnThree = findViewById(R.id.btn_three);
        mBtnThree.setOnClickListener(this);
        mBtnFour = findViewById(R.id.btn_four);
        mBtnFour.setOnClickListener(this);
        mBtnFive = findViewById(R.id.btn_five);
        mBtnFive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                startActivity(new Intent(MainActivity.this, OneActivity.class));
                break;
            case R.id.btn_two:
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
                break;
            case R.id.btn_three:
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
                break;
            case R.id.btn_four:
                startActivity(new Intent(MainActivity.this, FourActivity.class));
                break;
            case R.id.btn_five:
                startActivity(new Intent(MainActivity.this, FiveActivity.class));
                break;
            default:
                break;
        }
    }
}