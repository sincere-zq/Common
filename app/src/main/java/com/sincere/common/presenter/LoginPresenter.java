package com.sincere.common.presenter;

import android.text.TextUtils;

import com.sincere.common.api.ApiFactory;
import com.sincere.common.bean.LoginReq;
import com.sincere.common.bean.LoginRsp;
import com.sincere.common.contract.LoginContract;
import com.sincere.common.http.ObserverResponseListener;
import com.sincere.common.utils.MdWorkUtil;
import com.sincere.common.utils.PreferencesWrapper;
import com.sincere.common.utils.ToastUtil;

/**
 * 登录Presenter
 */
public class LoginPresenter extends LoginContract.ILoginPresenter {
    public LoginPresenter(LoginContract.ILoginModel model) {
        super(model);
    }

    @Override
    public void login(String phone, String password) {
        if (check(phone, password)) {
            model.login(getContext(), ApiFactory.getApi().login(new LoginReq(phone, MdWorkUtil.addMD5(password))), getTransformer(), new ObserverResponseListener<LoginRsp>() {
                @Override
                public void onNext(LoginRsp loginRsp) {
                    PreferencesWrapper.getPreferencesWrapper().saveUserInfo(loginRsp.member);
                    PreferencesWrapper.getPreferencesWrapper().saveUserToken(loginRsp.token);
                    if (getView() != null) {
                        getView().loginSuccess();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (getView() != null) {
                        getView().loginFail(e.getMessage());
                    }
                }
            });
        }

    }

    @Override
    public boolean check(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toastShortMessage("请输入手机号!!!");
            return false;
        }
        if (!MdWorkUtil.isMobile(phone)) {
            ToastUtil.toastShortMessage("手机号不合法!!!");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.toastShortMessage("请输入密码!!!");
            return false;
        }
        return true;
    }
}
