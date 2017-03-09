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

public class HomeFragment extends Fragment {

    /**图片资源*/
    private int[] images = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
    /**ImageView 容器*/
    private List<ImageView> imageViews;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager imageViewPager = (ViewPager) getView().findViewById(R.id.vp_top_image);
        //初始化数据
        imageViews = new ArrayList<>();
        for(int resId: images){
            ImageView iv = new ImageView(getContext());
            iv.setBackgroundResource(resId);
            imageViews.add(iv);
        }
        //给ViewPager设置适配器
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
        imageViewPager.setAdapter(imagePagerAdapter);
    }

    private class ImagePagerAdapter extends PagerAdapter{

        //1、获得ViewPager容器itemView的个数
        @Override
        public int getCount() {
            return imageViews.size();
        }

        //2、实例化每个itemView
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imageViews.get(position);
            container.addView(view);
            return view;
        }

        //3、判断是否是要显示的itemView，如果是就展示；
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //4，销毁itemView
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
