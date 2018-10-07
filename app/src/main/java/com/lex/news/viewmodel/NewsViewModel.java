package com.lex.news.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.lex.news.model.News;
import com.lex.news.network.action.NewsBaseResponse;
import com.lex.news.network.service.INews;
import com.lex.news.network.service.NetworkService;
import com.lex.news.network.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 新闻类
 * Created by lex on 2018/1/1.
 */

public class NewsViewModel extends ViewModel {
    private static final String TAG = NewsViewModel.class.getSimpleName();

    private MutableLiveData<List<News>> newsLiveData;

    public LiveData<List<News>> getNewsLiveData() {
        if (newsLiveData == null) {
            newsLiveData = new MutableLiveData<>();
        }
        remoteGetNews();
        return newsLiveData;
    }

    /**
     * 远程获取消息列表
     */
    private void remoteGetNews() {
        // 1、初始化Retrofit，得到INews动态代理对象
        INews service = NetworkService.getInstance().getRetrofit().create(INews.class);
        // 2、动态代理，生成OkHttpCall
        Call<NewsBaseResponse> newsCall = service.getNews(Url.TYPE_TOP, Url.APPKEY);

        Log.i(TAG, "start to send network message");

        // 3、利用OkHttp执行网络请求
        newsCall.enqueue(new Callback<NewsBaseResponse>() {
            @Override
            public void onResponse(Call<NewsBaseResponse> call, Response<NewsBaseResponse> response) {
                if (response == null || response.body() == null || response.body().result == null) return;

                newsLiveData.postValue(response.body().result.data);

                Log.i(TAG, "return: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<NewsBaseResponse> call, Throwable t) {
                Log.e(TAG, "onFailure()");

                if (t == null) return;

                Log.e(TAG, "failed: " + t.toString());
            }
        });
    }
}
