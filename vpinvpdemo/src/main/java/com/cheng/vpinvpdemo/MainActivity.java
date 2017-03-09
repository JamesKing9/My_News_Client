package com.cheng.vpinvpdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.vpinvpdemo.adapter.MyFragmentPagerAdapter;
import com.cheng.vpinvpdemo.fragment.HomeFragment;
import com.cheng.vpinvpdemo.fragment.NewsCenterFragment;
import com.cheng.vpinvpdemo.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);

        //初始化Fragment页面对象
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SettingFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        //给viewpager设置适配器
        vp.setAdapter(adapter);
    }
}
