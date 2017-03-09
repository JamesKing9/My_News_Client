# Module: Viewpager 嵌套 Viewpager

## 需求
* UI框架的Viewpager 中放置3个Fragment，其中HomeFragment加载的布局中又含有一个Viewpager用来展示4个图片页

## 步骤
### 一、第一个ViewPager 的逻辑
#### 1） activity_main.xml 中放置一个ViewPager

```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.cheng.vpinvpdemo.MainActivity">

    <android.support.v4.view.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>
```
#### 2） 新建3个简单的 Fragment：

SettingFragment，NewsCenterFragment，HomeFragment;

如，SettingFragment
```java
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

public class SettingFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setTextSize(20);
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        tv.setText("SettingFragment: 设置");
        return tv;
    }
}
```
#### 3） 加载activity_main，并找到ViewPager容器

```java
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
    }
}
```
#### 4 ）将3个Fragment放入容器

```java
//初始化Fragment页面对象,并放入容器中                       
List<Fragment> fragments = new ArrayList<>();  
fragments.add(new HomeFragment());             
fragments.add(new NewsCenterFragment());       
fragments.add(new SettingFragment());          
```

#### 5 ）给viewpager设置适配器

```java
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

        //初始化Fragment页面对象,并放入容器中
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SettingFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        //给viewpager设置适配器
        vp.setAdapter(adapter);
    }
}
```

##### 5.1）  实现自定义的 MyFragmentPagerAdapter 的逻辑

```java
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
```

#### 6） 到这里第一个ViewPager 的逻辑已经完成

### 二、第二个ViewPager的逻辑
* 第二个ViewPager的逻辑是，在HomeFragment中放一个Viewpager。

#### 7) 布局文件
home_fragment.xml
```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <android.support.v4.view.ViewPager
        android:id="@+id/vp_top_image"
        android:layout_width="match_parent"
        android:layout_height="200dp" />

    <TextView
        android:text="HomeFragment"
        android:gravity="center_horizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    
</LinearLayout>
```

#### 8) 创建 HomeFragment 的 view，加载Layout
```java
package com.cheng.vpinvpdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheng.vpinvpdemo.R;

public class HomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }
}
```
测试运行；

#### 9）初始化 HomeFragment，找到布局中ViewPager容器
```java
 @Override                                                               
 public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {         
     super.onViewCreated(view, savedInstanceState);                                  
     ViewPager vpTopImage = (ViewPager) getView().findViewById(R.id.vp_top_image);   
 } 
```

#### 10) 需求：ViewPager容器放置 3 个ImageView
* 图片资源
  `private int[] images = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};`
* ImageView 容器
  `private List<ImageView> imageViews;`
* 初始化数据
```java
imageViews = new ArrayList<>();
for(int resId: images){
	ImageView iv = new ImageView(getContext());
	iv.setBackgroundResource(resId);
	imageViews.add(iv);
}
```
#### 11)  给ViewPager设置适配器
```java
ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
imageViewPager.setAdapter(imagePagerAdapter);
```
* 完整代码：
```java
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
```

### END：该 Module 逻辑完成，测试运行--可行。






