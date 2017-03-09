package com.cheng.vpinvpdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <p>该FragmentPagerAdapter要做的事：</p>
 * <li>1、对当前类的属性进行初始化</li>
 * <li>2、得到 Fragment 的容器的大小</li>
 * <li>3、从装载 Fragment 的容器取得对应位置的 Fragment</li>
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    /**
     * 1、构造：对当前类的属性进行初始化
     * @param fm 传入Fragment管理器，有两种：1、普通的；2、v4支持库中的；
     * @param fragments 传入装载 Fragment 的容器
     */
    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    //2、得到 Fragment 的容器的大小
    @Override
    public int getCount() {
        return fragments.size();
    }

    //3、从装载 Fragment 的容器取得对应位置的 Fragment
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
