package com.sincere.common.presenter;

import android.text.TextUtils;

import com.sincere.common.api.ApiFactory;
import com.sincere.common.bean.RegistReq;
import com.sincere.common.bean.SendSmsReq;
import com.sincere.common.bean.UserInfo;
import com.sincere.common.contract.RegistContract;
import com.sincere.common.http.ObserverResponseListener;
import com.sincere.common.utils.MdWorkUtil;
import com.sincere.common.utils.PreferencesWrapper;
import com.sincere.common.utils.ToastUtil;

/**
 * 注册Presenter
 */
public class RegistPresenter extends RegistContract.IRegistPresenter {
    public RegistPresenter(RegistContract.IRegistModel model) {
        super(model);
    }

    @Override
    public void sendSms() {
        if (getView() != null) {
            String phone = getView().checkPhone();
            if (TextUtils.isEmpty(phone)) {
                ToastUtil.toastShortMessage("请输入手机号!!!");
                return;
            }
            if (!MdWorkUtil.isMobile(phone)) {
                ToastUtil.toastShortMessage("手机号不合法!!!");
                return;
            }
            getView().startDownTimer();
            SendSmsReq req = new SendSmsReq(phone, 0, phone);
            model.sendSms(getContext(), ApiFactory.getApi().sendSms(req), getTransformer(), new ObserverResponseListener() {
                @Override
                public void onNext(Object s) {
                    ToastUtil.toastShortMessage("发送成功");
                }

                @Override
                public void onError(Throwable e) {

                }
            });

        }
    }

    @Override
    public void regist() {
        if (getView() != null) {
            if (checkRegistInfo()) {
                model.regist(getContext(),
                        ApiFactory.getApi().regist(new RegistReq(getView().checkPhone(), getView().checkCode(), MdWorkUtil.addMD5(getView().checkPwd()))),
                        getTransformer(), new ObserverResponseListener<UserInfo>() {
                            @Override
                            public void onNext(UserInfo userInfo) {
                                PreferencesWrapper.getPreferencesWrapper().saveUserInfo(userInfo);
                                ToastUtil.toastShortMessage("注册成功");
                                getView().registSuccess();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        }
    }

    @Override
    protected boolean checkRegistInfo() {
        if (TextUtils.isEmpty(getView().checkPhone())) {
            ToastUtil.toastShortMessage("请输入手机号!!!");
            return false;
        }
        if (!MdWorkUtil.isMobile(getView().checkPhone())) {
            ToastUtil.toastShortMessage("手机号不合法!!!");
            return false;
        }
        if (TextUtils.isEmpty(getView().checkCode())) {
            ToastUtil.toastShortMessage("请输入验证码!!!");
            return false;
        }
        String pwd = getView().checkPwd();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.toastShortMessage("请输入密码!!!");
            return false;
        }
        String pwdAgain = getView().checkPwdAgin();
        if (TextUtils.isEmpty(pwdAgain)) {
            ToastUtil.toastShortMessage("请再次输入密码!!!");
            return false;
        }
        if (!TextUtils.equals(pwd, pwdAgain)) {
            ToastUtil.toastShortMessage("两次密码不一致，请重新输入!!!");
            return false;
        }
        return true;
    }
}
