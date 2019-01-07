package com.sincere.common.model;

import android.content.Context;

import com.sincere.common.bean.UserInfo;
import com.sincere.common.contract.RegistContract;
import com.sincere.common.http.ObserverResponseListener;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 注册model
 */
public class RegistModel extends RegistContract.IRegistModel {
    @Override
    public void sendSms(Context context, Observable observable, ObservableTransformer transformer, ObserverResponseListener listener) {
        subscribe(context, observable, listener, transformer);
    }

    @Override
    public void regist(Context context, Observable observable, ObservableTransformer transformer, ObserverResponseListener<UserInfo> listener) {
        subscribe(context, observable, listener, transformer);
    }
}
