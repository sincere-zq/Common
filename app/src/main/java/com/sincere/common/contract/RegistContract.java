package com.sincere.common.contract;

import android.content.Context;

import com.sincere.common.base.BaseModel;
import com.sincere.common.base.BasePresenter;
import com.sincere.common.base.BaseView;
import com.sincere.common.bean.UserInfo;
import com.sincere.common.http.ObserverResponseListener;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 契约类
 */
public interface RegistContract {
    interface IRegistView extends BaseView {
        String checkPhone();

        String checkCode();

        String checkPwd();

        String checkPwdAgin();

        void startDownTimer();

        void registSuccess();
    }

    abstract class IRegistModel extends BaseModel {
        public abstract void sendSms(Context context,
                                     final Observable observable,
                                     ObservableTransformer transformer,
                                     ObserverResponseListener listener);

        public abstract void regist(Context context,
                                    final Observable observable,
                                    ObservableTransformer transformer,
                                    ObserverResponseListener<UserInfo> listener
        );
    }

    abstract class IRegistPresenter extends BasePresenter<IRegistView> {
        protected IRegistModel model;

        public IRegistPresenter(IRegistModel model) {
            this.model = model;
        }

        public abstract void sendSms();

        public abstract void regist();

        protected abstract boolean checkRegistInfo();
    }
}
