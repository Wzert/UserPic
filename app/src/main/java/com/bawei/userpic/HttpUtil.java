package com.bawei.userpic;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class HttpUtil {
    private static HttpUtil util;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private HttpUtil(){
        okHttpClient = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpUtil getUtil(){
        if(util==null){
            synchronized (HttpUtil.class){
                if(util==null){
                    util=new HttpUtil();
                }
            }
        }
        return util;
    }

    public <T> T tClass(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
