package com.sincere.common.presenter;

import com.sincere.common.contract.MainContract;

public class MainPresenter extends MainContract.IMainPresenter {

    public MainPresenter(MainContract.IMainModel model) {
        super(model);
    }

    @Override
    public void getData() {
    }
}
