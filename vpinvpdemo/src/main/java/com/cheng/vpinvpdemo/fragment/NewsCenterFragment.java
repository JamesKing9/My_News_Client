package com.cheng.vpinvpdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Apple on 2016/9/21.
 */
public class NewsCenterFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setTextSize(20);
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        tv.setText("NewsCenterFragment: 新闻中心");
        return tv;
    }
}
