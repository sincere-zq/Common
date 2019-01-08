package com.sincere.common.contract;

import android.content.Context;
import android.os.Bundle;

import com.netson.canlenderdemo.calendar.SimpleMonthAdapter;
import com.sincere.common.base.BaseModel;
import com.sincere.common.base.BasePresenter;
import com.sincere.common.base.BaseView;
import com.sincere.common.http.ObserverResponseListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 契约类
 */
public interface ReserveContract {
    interface IReserveView extends BaseView {
        void setReserveDate(SimpleMonthAdapter.CalendarDay checkInDay, SimpleMonthAdapter.CalendarDay checkOutDay);

        void selectLiveDate(Bundle bundle);

        void banner(List<String> bannerList);
    }

    abstract class IReserveModel extends BaseModel {
        public abstract void getBanner(Context context,
                                       final Observable observable,
                                       ObservableTransformer transformer,
                                       ObserverResponseListener<List<String>> listener
        );
    }

    abstract class IReservePresenter extends BasePresenter<IReserveView> {
        protected IReserveModel model;

        public IReservePresenter(IReserveModel model) {
            this.model = model;
        }

        public abstract void initReserveDate();

        public abstract void banner();

        public abstract void selectLiveDate();

        public abstract void selectLiveDateBack(SimpleMonthAdapter.CalendarDay checkInDay, SimpleMonthAdapter.CalendarDay checkOutDay);


    }
}
