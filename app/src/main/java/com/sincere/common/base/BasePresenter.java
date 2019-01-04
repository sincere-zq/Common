package com.sincere.common.base;

import android.content.Context;

import io.reactivex.ObservableTransformer;

/**
 * P层父类
 *
 * @param <V>
 */
public abstract class BasePresenter<V extends BaseView> {

    private V mView;

    private Context mContext;

    private ObservableTransformer mTransformer;

    public V getView() {
        return mView;
    }

    public void attachView(V v) {
        mView = v;
    }

    public void detachView() {
        mView = null;
    }

    public void detachContext() {
        mContext = null;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public ObservableTransformer getTransformer() {
        return mTransformer;
    }

    public void bindLifecycle(ObservableTransformer transformer) {
        mTransformer = transformer;
    }

}
