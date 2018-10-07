package com.lex.news.view.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器
 * Created by lex on 2017/3/5.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private List<T> mData;

    /**
     * 子类实现的两个方法
     * @return 布局id
     */
    public abstract int getLayoutId();
    public abstract void onBindViewHolderImpl(BaseViewHolder holder, int position);

    /**
     * 刷新数据
     */
    public void refreshData(List<T> data) {
        if (data == null) {
            mData = new ArrayList<>();
        } else {
            mData = data;
        }
        notifyDataSetChanged();
    }

    /**
     * 获取数据
     * @return 数据
     */
    public List<T> getData() {
        if (mData == null) {
            return new ArrayList<>();
        }
        return mData;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        onBindViewHolderImpl(holder, position);
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }
}
