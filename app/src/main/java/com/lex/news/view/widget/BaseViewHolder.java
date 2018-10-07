package com.lex.news.view.widget;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * 基础ViewHolder
 * Created by lex on 2017/3/5.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T> T findViewbyId(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    /**
     * 设置文本
     * @param id id
     * @param text 文本
     */
    public void setText(int id, String text) {
        TextView textView = findViewbyId(id);
        textView.setText(text);
    }
}
