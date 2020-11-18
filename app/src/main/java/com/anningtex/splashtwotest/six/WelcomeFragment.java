package com.anningtex.splashtwotest.six;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.anningtex.splashtwotest.R;

/**
 * @author Administrator
 */
public class WelcomeFragment extends Fragment {
    View view = null;
    int imgId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.welcome_fragment, null);
        ImageView ivWelcome = view.findViewById(R.id.welcome_Img);
        ivWelcome.setBackgroundResource(imgId);
        return view;
    }

    /**
     * 为该Fragment设置显示图片
     */
    public void setImg(int imgID) {
        imgId = imgID;
    }
}
