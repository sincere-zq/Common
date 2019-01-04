package com.sincere.common.activity;

import com.sincere.common.R;
import com.sincere.common.base.BaseActivity;
import com.sincere.common.contract.MainContract;
import com.sincere.common.model.MainModel;
import com.sincere.common.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainContract.IMainView, MainContract.IMainPresenter> implements MainContract.IMainView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.IMainPresenter createPresenter() {
        return new MainPresenter(new MainModel());
    }

    @Override
    public MainContract.IMainView createView() {
        return this;
    }

    @Override
    public void init() {

    }

}
