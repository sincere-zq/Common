package com.sincere.common.activity;

import android.text.TextUtils;

import com.sincere.common.R;
import com.sincere.common.base.BaseActivity;
import com.sincere.common.base.BasePresenter;
import com.sincere.common.base.BaseView;
import com.sincere.common.tools.BackgroundTasks;
import com.sincere.common.utils.PreferencesWrapper;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        BackgroundTasks.getInstance().delay(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(PreferencesWrapper.getPreferencesWrapper().getUserToken())) {
                    openActivity(MainActivity.class);
                    finish();
                }
            }
        }, 2000);
    }
}
