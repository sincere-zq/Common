package com.sincere.common.presenter;

import android.os.Bundle;

import com.netson.canlenderdemo.calendar.SimpleMonthAdapter;
import com.sincere.common.activity.CalendarActivity;
import com.sincere.common.api.ApiFactory;
import com.sincere.common.contract.ReserveContract;
import com.sincere.common.http.ObserverResponseListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 预定presenter
 */
public class ReservePresenter extends ReserveContract.IReservePresenter {
    private SimpleMonthAdapter.CalendarDay checkInDay, checkOutDay;

    public ReservePresenter(ReserveContract.IReserveModel model) {
        super(model);
    }

    @Override
    public void initReserveDate() {
        checkInDay = new SimpleMonthAdapter.CalendarDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        checkInDay.year = calendar.get(Calendar.YEAR);
        checkInDay.month = calendar.get(Calendar.MONTH);
        checkInDay.day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.DATE, 1);

        checkOutDay = new SimpleMonthAdapter.CalendarDay();
        checkOutDay.year = calendar.get(Calendar.YEAR);
        checkOutDay.month = calendar.get(Calendar.MONTH);
        checkOutDay.day = calendar.get(Calendar.DAY_OF_MONTH);

        if (getView() != null) {
            getView().setReserveDate(checkInDay, checkOutDay);
        }

    }

    @Override
    public void banner() {
        model.getBanner(getContext(), ApiFactory.getApi().banner(), getTransformer(), new ObserverResponseListener<List<String>>() {
            @Override
            public void onNext(List<String> strings) {
                if (getView() != null) {
                    getView().banner(strings);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void selectLiveDate() {
        if (getView() != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CalendarActivity.CHECK_IN_DAY, checkInDay);
            bundle.putSerializable(CalendarActivity.CHECK_OUT_DAY, checkOutDay);
            getView().selectLiveDate(bundle);
        }
    }

    @Override
    public void selectLiveDateBack(SimpleMonthAdapter.CalendarDay checkInDay, SimpleMonthAdapter.CalendarDay checkOutDay) {
        if (getView() != null) {
            this.checkInDay = checkInDay;
            this.checkOutDay = checkOutDay;
            getView().setReserveDate(checkInDay, checkOutDay);
        }
    }
}
