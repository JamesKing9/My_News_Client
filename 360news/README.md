# 注意：
* 1 修改list1.json文件中的ip为我们自己的ip
* 2 修改 url 为我们的ip

# 做了什么？
* 1 recyclerview展示不同类型的item
* 2 自定义view：RecycleViewDivider，给条目设置分割线
* 3 ButterKnife8：ButterKnife.bind(this);
* 4 photoview;
* 5 WebView 和 js 互调；


联网获取数据
        OkHttp

dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')

    testCompile 'junit:junit:4.12'

    compile 'com.android.support:appcompat-v7:23.4.0'

    compile 'com.jakewharton:butterknife:8.4.0'
    apt 'com.jakewharton:butterknife-compiler:8.4.0'
    1  @BindView(R.id.webView)


    compile 'com.squareup.picasso:picasso:2.5.2'

    compile 'com.zhy:okhttputils:2.6.2'

    compile 'com.google.code.gson:gson:2.2.4'

    compile 'com.android.support:recyclerview-v7:23.4.0'

    compile files('libs/photoview.jar')
}