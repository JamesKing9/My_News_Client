package com.cheng.a360news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.cheng.a360news.adapter.NewsAdapter;
import com.cheng.a360news.bean.ResuleBean;
import com.cheng.a360news.view.RecycleViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //RecyclerView的初始化
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,1, Color.BLACK));


        /**## 服务器主机 ip ##**/
        /**服务器在电脑上,直接ip访问*/
//        public static final String HOST = "http://192.168.0.103:8080/GooglePlayServer/";
        /**服务器在手机上*/
//        public static final String HOST = "http://127.0.0.1:8080/GooglePlayServer/";
        /**服务器在电脑上,android模拟器访问*/
//        public static final String HOST = "http://10.0.2.2:8080/GooglePlayServer/";
        /**服务器在电脑上,genymotion模拟器访问*/
//        public static final String HOST = "http://10.0.3.2:8080/GooglePlayServer/";
//        String url = "http://10.0.2.2:8080/360/list1.json";
        String url = "http://10.0.3.2:8080/360/list1.json";

        //联网获取数据
        OkHttpUtils     /*compile 'com.zhy:okhttputils:2.6.2'*/
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResuleBean resuleBean = new Gson().fromJson(response, ResuleBean.class);
                        rv.setAdapter(new NewsAdapter(resuleBean.data, getApplicationContext()));
                    }
                });
    }


}
