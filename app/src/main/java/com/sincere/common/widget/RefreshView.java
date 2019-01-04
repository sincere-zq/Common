package com.sincere.common.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class RefreshView extends EasyRefreshLayout {
    private RecyclerView recycler;
    private BaseQuickAdapter adapter;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        recycler = new RecyclerView(context);
        addView(recycler);
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
        recycler.setAdapter(adapter);
    }

    /**
     * 设置LinearLayoutManager
     */
    public void setLinearLayoutManager() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 设置GridLayoutManager
     *
     * @param spanCount 每行个数
     */
    public void setGridLayoutManager(int spanCount) {
        recycler.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
    }
}
