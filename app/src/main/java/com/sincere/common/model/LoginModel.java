package com.sincere.common.model;

import android.content.Context;

import com.sincere.common.bean.LoginRsp;
import com.sincere.common.contract.LoginContract;
import com.sincere.common.http.ObserverResponseListener;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 登录model
 */
public class LoginModel extends LoginContract.ILoginModel {
    @Override
    public void login(Context context, Observable observable, ObservableTransformer transformer, ObserverResponseListener<LoginRsp> listener) {
        subscribe(context, observable, listener, transformer);
    }
}
