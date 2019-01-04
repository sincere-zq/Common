package com.sincere.common.model;

import android.content.Context;

import com.sincere.common.contract.MainContract;
import com.sincere.common.http.ObserverResponseListener;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class MainModel extends MainContract.IMainModel {
    @Override
    public void getMainData(Context context, Observable observable, ObserverResponseListener listener, ObservableTransformer transformer) {

    }
}
