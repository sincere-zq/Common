package com.sincere.common.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sincere.common.R;
import com.sincere.common.base.BaseActivity;
import com.sincere.common.bean.UserInfo;
import com.sincere.common.contract.LoginContract;
import com.sincere.common.model.LoginModel;
import com.sincere.common.presenter.LoginPresenter;
import com.sincere.common.utils.MdWorkUtil;
import com.sincere.common.utils.PreferencesWrapper;
import com.sincere.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.ILoginView, LoginContract.ILoginPresenter> implements LoginContract.ILoginView {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginContract.ILoginPresenter createPresenter() {
        return new LoginPresenter(new LoginModel());
    }

    @Override
    public LoginContract.ILoginView createView() {
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        UserInfo userInfo = PreferencesWrapper.getPreferencesWrapper().getUserInfo();
        if (userInfo != null) {
            etUserName.setText(userInfo.phone);
            etPassword.setText(MdWorkUtil.convertMD5(userInfo.loginPwd));
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_regist, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getPresenter().login(etUserName.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.tv_regist:
                openActivity(RegistActivity.class);
                break;
            case R.id.tv_forget_password:
                break;
        }
    }

    @Override
    public void loginSuccess() {
        ToastUtil.toastShortMessage("登录成功");
        openActivity(MainActivity.class);
        finish();
    }

    @Override
    public void loginFail(String errorMsg) {
        ToastUtil.toastShortMessage(errorMsg);
    }
}
