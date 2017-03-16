package com.cheng.a360news.bean;

import java.util.List;

public class ResuleBean {
    public List<NewsData> data;
    public int error;

    public class NewsData{
        public int id;
        public String title;
        public String weburl;
        public List<String> imgs;
    }
}
