package com.sincere.common.activity;

import android.content.Intent;
import android.os.Bundle;

import com.netson.canlenderdemo.calendar.CalendarSelectLayout;
import com.netson.canlenderdemo.calendar.SimpleMonthAdapter;
import com.sincere.common.R;
import com.sincere.common.base.BaseActivity;
import com.sincere.common.base.BasePresenter;
import com.sincere.common.base.BaseView;
import com.sincere.common.widget.TitleBar;

import butterknife.BindView;

/**
 * 选择日期
 */
public class CalendarActivity extends BaseActivity implements CalendarSelectLayout.OnSelectedTimeListener {
    public static final String CHECK_IN_DAY = "check_in_day";
    public static final String CHECK_OUT_DAY = "check_out_day";
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.calendar_layout)
    CalendarSelectLayout calendarLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_calendar;
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
        calendarLayout.setOnSelectedTimeListener(this);
        SimpleMonthAdapter.CalendarDay firstDay = (SimpleMonthAdapter.CalendarDay) getIntent().getSerializableExtra(CHECK_IN_DAY);
        SimpleMonthAdapter.CalendarDay lastDay = (SimpleMonthAdapter.CalendarDay) getIntent().getSerializableExtra(CHECK_OUT_DAY);
        calendarLayout.setCheckedDays(firstDay, lastDay);
    }

    @Override
    public void onTimeSelected(SimpleMonthAdapter.CalendarDay firstDay, SimpleMonthAdapter.CalendarDay lastDay) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CHECK_IN_DAY, firstDay);
        bundle.putSerializable(CHECK_OUT_DAY, lastDay);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }
}
