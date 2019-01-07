package com.sincere.common.contract;

import android.content.Context;

import com.sincere.common.base.BaseModel;
import com.sincere.common.base.BasePresenter;
import com.sincere.common.base.BaseView;
import com.sincere.common.bean.LoginRsp;
import com.sincere.common.http.ObserverResponseListener;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 契约类
 */
public interface LoginContract {
    interface ILoginView extends BaseView {
        void loginSuccess();

        void loginFail(String errorMsg);
    }

    abstract class ILoginModel extends BaseModel {
        public abstract void login(Context context,
                                     final Observable observable,
                                     ObservableTransformer transformer,
                                     ObserverResponseListener<LoginRsp> listener
        );
    }

    abstract class ILoginPresenter extends BasePresenter<ILoginView> {
        protected ILoginModel model;

        public ILoginPresenter(ILoginModel model) {
            this.model = model;
        }

        public abstract void login(String phone, String password);

        public abstract boolean check(String phone, String password);
    }
}
