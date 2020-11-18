package com.anningtex.splashtwotest.four;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anningtex.splashtwotest.R;
import com.anningtex.splashtwotest.act.FourActivity;
import com.anningtex.splashtwotest.adapter.GuideViewPagerAdapter;
import com.anningtex.splashtwotest.constant.AppConstants;
import com.anningtex.splashtwotest.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class WelcomeGuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vpGuide;
    private GuideViewPagerAdapter adapter;
    private List<View> viewList;
    private Button btnStart;

    /**
     * 引导页图片资源
     */
    private static final int[] pics = {R.layout.guid_view1, R.layout.guid_view2, R.layout.guid_view3, R.layout.guid_view4};

    /**
     * 底部小点图片
     */
    private ImageView[] dots;

    /**
     * 记录当前选中位置
     */
    private int currentIndex;

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
        setContentView(R.layout.activity_welcome_guide);
        initView();
        initDots();
    }

    private void initView() {
        viewList = new ArrayList<>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            if (i == pics.length - 1) {
                btnStart = view.findViewById(R.id.btn_login);
                btnStart.setTag("enter");
                btnStart.setOnClickListener(this);
            }
            viewList.add(view);
        }

        vpGuide = findViewById(R.id.vp_guide);
        adapter = new GuideViewPagerAdapter(viewList);
        vpGuide.setAdapter(adapter);
        vpGuide.setOnPageChangeListener(new PageChangeListener());
    }

    private void initDots() {
        LinearLayout llOne = findViewById(R.id.ll_one);
        dots = new ImageView[pics.length];
        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) llOne.getChildAt(i);
            // 都设为灰色
            dots[i].setEnabled(false);
            dots[i].setOnClickListener(this);
            // 设置位置tag，方便取出与当前位置对应
            dots[i].setTag(i);
        }
        currentIndex = 0;
        // 设置为白色，即选中状态
        dots[currentIndex].setEnabled(true);
    }

    /**
     * 设置当前view
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vpGuide.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    private void enterMainActivity() {
        startActivity(new Intent(WelcomeGuideActivity.this, FourActivity.class));
        SpUtils.putBoolean(WelcomeGuideActivity.this, AppConstants.FIRST_OPEN, true);
        finish();
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置
        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurDot(position);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
        SpUtils.putBoolean(WelcomeGuideActivity.this, AppConstants.FIRST_OPEN, true);
        finish();
    }
}