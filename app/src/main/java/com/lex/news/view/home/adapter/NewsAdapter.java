package com.lex.news.view.home.adapter;

import com.lex.news.view.widget.BaseAdapter;
import com.lex.news.view.widget.BaseViewHolder;
import com.lex.news.R;

import com.lex.news.model.News;

/**
 * 适配器
 * Created by lex on 2017/3/5.
 */

public class NewsAdapter extends BaseAdapter<News>{
    @Override
    public int getLayoutId() {
        return R.layout.item_home;
    }

    @Override
    public void onBindViewHolderImpl(BaseViewHolder holder, int position) {
        News news = getData().get(position);
        holder.setText(R.id.text, news.getTitle());
    }
}
