package com.lex.news.network.service;

import com.lex.news.network.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络服务类
 * Created by lex on 2017/3/4.
 * 使用文档：http://square.github.io/retrofit/
 */

public class NetworkService {
    private static NetworkService INSTANCE;
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Url.URL_GET_NEWS)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (INSTANCE == null) {
            synchronized (NetworkService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetworkService();
                }
            }
        }
        return INSTANCE;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
