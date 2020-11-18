package com.anningtex.splashtwotest.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anningtex.splashtwotest.R;
import com.anningtex.splashtwotest.six.WelcomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class SixActivity extends AppCompatActivity {
    private ViewPager welcome;
    private LinearLayout directorLayout;
    private List<Integer> imgS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        initView();
    }

    private void initView() {
        imgS = new ArrayList<>();
        imgS.add(R.mipmap.help1);
        imgS.add(R.mipmap.help2);
        imgS.add(R.mipmap.help3);
        imgS.add(R.mipmap.help4);

        directorLayout = findViewById(R.id.director);
        welcome = findViewById(R.id.welcome_pager);
        welcome.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        welcome.setOnPageChangeListener(new pageChangeListener());
    }

    public class pagerAdapter extends FragmentPagerAdapter {

        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            WelcomeFragment fm = new WelcomeFragment();
            fm.setImg(imgS.get(arg0));
            return fm;
        }

        @Override
        public int getCount() {
            return imgS.size();
        }
    }

    public class pageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当某一个页面被选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {
            int count = directorLayout.getChildCount();
            /**
             * 指示器自对象顺序和页面显示顺序一样的设置为on，其余的设置为off
             * */
            for (int i = 0; i < count; i++) {
                ImageView iv = (ImageView) directorLayout.getChildAt(i);
                if (i == arg0) {
                    iv.setBackgroundResource(R.mipmap.pageindicator_on);
                } else {
                    iv.setBackgroundResource(R.mipmap.pageindicator_off);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }
}