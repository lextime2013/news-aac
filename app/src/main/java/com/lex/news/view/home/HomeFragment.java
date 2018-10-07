package com.lex.news.view.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lex.news.viewmodel.NewsViewModel;
import com.lex.news.view.widget.BaseAdapter;
import com.lex.news.view.BaseFragment;
import com.lex.news.R;
import com.lex.news.model.News;
import com.lex.news.view.home.adapter.NewsAdapter;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.recycler_news)
    RecyclerView recyclerNews;
    private BaseAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.content_home;
    }

    /**
     * 初始化view
     */
    @Override
    protected void initView() {
        mAdapter = new NewsAdapter();
        recyclerNews.setAdapter(mAdapter);
        recyclerNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getNewsLiveData().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                mAdapter.refreshData(news);
            }
        });

    }
}
