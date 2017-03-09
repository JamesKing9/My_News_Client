package com.cheng.a360news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.a360news.NewsDetailActivity;
import com.cheng.a360news.R;
import com.cheng.a360news.bean.ResuleBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Apple on 2016/10/8.
 */
public class NewsAdapter extends RecyclerView.Adapter {

    private List<ResuleBean.NewsData> newsDataList;
    private Context context;

    /**
     * <p>构造中初始化该类的属性</p>
     *
     * @param newsDataList 传入要adapter中需要的数据
     * @param context 给该RecyclerView设置 adapter 的时候传入RecyclerView所在的容器的 Context。
     */
    public NewsAdapter(List<ResuleBean.NewsData> newsDataList, Context context) {
        this.newsDataList = newsDataList;
        this.context = context;
    }

    //区分条目的类型
    @Override
    public int getItemViewType(int position) {
        ResuleBean.NewsData newsData = newsDataList.get(position);
        if (newsData.imgs.size() == 3) {//显示三张图片
            return 0;
        } else if (newsData.imgs.size() == 0) {//显示文字
            return 1;
        } else if (newsData.imgs.size() == 1) {//显示一张图片和文字
            return 2;
        }
        return super.getItemViewType(position);
    }

    // 初始化item容器
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {//显示三张图片 的条目
            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 1) {//显示文字
            View view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == 2) {//显示一张图片和文字
            View view = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
            return new ViewHolder3(view);
        }
        //如果上面的条件都不符合，则返回null
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //取出对应位置的新闻数据
        final ResuleBean.NewsData newsData = newsDataList.get(position);
        if (newsData.imgs.size() == 3) {//显示三张图片
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            viewHolder1.tvTitle.setText(newsData.title);
            Picasso.with(context).load(newsData.imgs.get(0)).into(viewHolder1.iv1);
            Picasso.with(context).load(newsData.imgs.get(1)).into(viewHolder1.iv2);
            Picasso.with(context).load(newsData.imgs.get(2)).into(viewHolder1.iv3);
            //条目的点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    intent.putExtra("url", newsData.weburl);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        } else if (newsData.imgs.size() == 0) {//显示文字
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.tvTitle.setText(newsData.title);
        } else if (newsData.imgs.size() == 1) {//显示一张图片和文字
            ViewHolder3 viewHolder3 = (ViewHolder3) holder;
            viewHolder3.tvTitle.setText(newsData.title);
            Picasso.with(context).load(newsData.imgs.get(0)).into(viewHolder3.iv1);
        }
    }

    //要展示的条目总数
    @Override
    public int getItemCount() {
        return newsDataList != null ? newsDataList.size() : 0;
    }

    //因为有3个类型的item，所有创建三个item
    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv1)
        ImageView iv1;
        @BindView(R.id.iv2)
        ImageView iv2;
        @BindView(R.id.iv3)
        ImageView iv3;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv1)
        ImageView iv1;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
