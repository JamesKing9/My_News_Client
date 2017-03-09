package com.cheng.vpinvpdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheng.vpinvpdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apple on 2016/9/21.
 */
public class HomeFragment extends Fragment {

    private int[] imags = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
    private List<ImageView> views;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager vpTopImage = (ViewPager) getView().findViewById(R.id.vp_top_image);
        //初始化数据
        views = new ArrayList<>();
        for(int resId:imags){
            ImageView iv = new ImageView(getContext());
            iv.setBackgroundResource(resId);
            views.add(iv);
        }
        //给ViewPager设置适配器
        MyTopImageAdapter adapter = new MyTopImageAdapter();
        vpTopImage.setAdapter(adapter);
    }

    private class MyTopImageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
