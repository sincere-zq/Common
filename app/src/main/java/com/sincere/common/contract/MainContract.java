package com.sincere.common.contract;

import android.content.Context;

import com.sincere.common.base.BaseModel;
import com.sincere.common.base.BasePresenter;
import com.sincere.common.base.BaseView;
import com.sincere.common.http.ObserverResponseListener;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 契约类
 */
public interface MainContract {
    interface IMainView extends BaseView {

    }

    abstract class IMainModel extends BaseModel {
        public abstract void getMainData(Context context,
                                         final Observable observable,
                                         ObserverResponseListener listener,
                                         ObservableTransformer transformer);
    }

    abstract class IMainPresenter extends BasePresenter<IMainView> {
        protected IMainModel model;

        public IMainPresenter(IMainModel model) {
            this.model = model;
        }

        public abstract void getData();
    }
}
