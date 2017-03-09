package com.cheng.a360news;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * 活动界面：展示网页中的大图展示
 * 点击网页中的图片，展示大图
 */
public class ShowActivity extends Activity {

    @BindView(R.id.photoView)
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");
        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();

        //显示图片
        Picasso
                .with(this) //作用：？
                .load(url)  //加载url
                .into(photoView); /*Asynchronously fulfills(异步填充) the request into the specified ImageView.
Note: This method keeps a weak reference（弱引用） to the ImageView instance
and will automatically support object recycling(自动支持对象回收).*/
    }
}
