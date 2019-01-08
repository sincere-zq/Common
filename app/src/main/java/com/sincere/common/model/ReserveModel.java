package com.sincere.common.model;

import android.content.Context;

import com.sincere.common.contract.ReserveContract;
import com.sincere.common.http.ObserverResponseListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 预定model
 */
public class ReserveModel extends ReserveContract.IReserveModel {
    @Override
    public void getBanner(Context context, Observable observable, ObservableTransformer transformer, ObserverResponseListener<List<String>> listener) {
        subscribe(context, observable, listener, transformer);
    }
}
