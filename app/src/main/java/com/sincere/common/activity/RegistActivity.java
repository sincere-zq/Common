package com.sincere.common.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sincere.common.R;
import com.sincere.common.base.BaseActivity;
import com.sincere.common.contract.RegistContract;
import com.sincere.common.model.RegistModel;
import com.sincere.common.presenter.RegistPresenter;
import com.sincere.common.tools.TimeCount;
import com.sincere.common.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegistActivity extends BaseActivity<RegistContract.IRegistView, RegistContract.IRegistPresenter> implements RegistContract.IRegistView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_agin)
    EditText etPasswordAgin;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    TimeCount timeCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public RegistContract.IRegistPresenter createPresenter() {
        return new RegistPresenter(new RegistModel());
    }

    @Override
    public RegistContract.IRegistView createView() {
        return this;
    }

    @Override
    public void init() {
        timeCount = new TimeCount(this, 60000, btnGetCode);
    }

    @OnClick({R.id.btn_get_code, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                getPresenter().sendSms();
                break;
            case R.id.btn_regist:
                getPresenter().regist();
                break;
        }
    }

    @Override
    public String checkPhone() {
        return etPhone.getText().toString();
    }

    @Override
    public String checkCode() {
        return etCode.getText().toString();
    }

    @Override
    public String checkPwd() {
        return etPassword.getText().toString();
    }

    @Override
    public String checkPwdAgin() {
        return etPasswordAgin.getText().toString();
    }

    @Override
    public void startDownTimer() {
        timeCount.start();
    }

    @Override
    public void registSuccess() {
        finish();
    }
}
