package com.lex.news.network.service;

import com.lex.news.network.action.NewsBaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 获取新闻接口
 * Created by lex on 2017/3/4.
 */

public interface INews {
    @GET("index")
    Call<NewsBaseResponse> getNews(@Query("type") String type, @Query("key") String key);
}
