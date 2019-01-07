package com.sincere.common.base;

import android.content.Context;
import android.util.Log;

import com.sincere.common.http.HttpThrowable;
import com.sincere.common.http.ObserverResponseListener;
import com.sincere.common.http.ProgressCancelListener;
import com.sincere.common.http.ProgressDialogHandler;
import com.sincere.common.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 观察者
 */
public class BaseObserver<T> implements Observer<BaseRsp<T>>, ProgressCancelListener {
    private static final String TAG = "ProgressObserver____ ";
    private ObserverResponseListener<T> listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Disposable d;

    public BaseObserver(Context context, ObserverResponseListener<T> listener, boolean isDialog, boolean cancelable) {
        this.listener = listener;
        if (isDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.e(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(BaseRsp<T> t) {
        if (t != null) {
            if (t.code == 200) {
                listener.onNext(t.content);//可定制接口，通过code回调处理不同的业务
            } else {
                listener.onError(new HttpThrowable(t.message));
            }
        } else {
            listener.onError(new Throwable());
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Log.e(TAG, "onError: ", e);
        //自定义异常处理
        if (e instanceof UnknownHostException) {
            ToastUtil.toastShortMessage("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.toastShortMessage("请求超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.toastShortMessage("连接失败");
        } else if (e instanceof HttpException) {
            ToastUtil.toastShortMessage("请求超时");
        } else if (e instanceof HttpThrowable) {
            ToastUtil.toastShortMessage(((HttpThrowable) e).message);
        } else {
            ToastUtil.toastShortMessage("请求失败");
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.e(TAG, "onComplete: ");
    }

    @Override
    public void onCancelProgress() {
        Log.e(TAG, "requestCancel: ");
        //如果处于订阅状态，则取消订阅
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
