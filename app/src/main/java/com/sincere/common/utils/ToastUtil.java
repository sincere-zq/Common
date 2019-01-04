package com.sincere.common.utils;

import android.widget.Toast;

import com.sincere.common.app.CommonApplication;
import com.sincere.common.tools.BackgroundTasks;


/**
 * 吐司通用方法类
 */
public class ToastUtil {
    private static Toast mToast;

    public static final void toastLongMessage(final String message) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(CommonApplication.getApplication(), message,
                        Toast.LENGTH_LONG);
                // mToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                mToast.show();
            }
        });
    }

    public static final void toastLongMessage(final int messageId) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(CommonApplication.getApplication(), messageId,
                        Toast.LENGTH_LONG);
                // mToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                mToast.show();
            }
        });
    }


    public static final void toastShortMessage(final String message) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(CommonApplication.getApplication(), message,
                        Toast.LENGTH_SHORT);
                // mToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                mToast.show();
            }
        });
    }

    public static final void toastShortMessage(final int messageId) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(CommonApplication.getApplication(), messageId,
                        Toast.LENGTH_SHORT);
                // mToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                mToast.show();
            }
        });
    }

}
