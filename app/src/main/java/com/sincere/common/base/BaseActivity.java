package com.sincere.common.base;

import android.content.Intent;
import android.os.Bundle;

import com.sincere.common.tools.AppManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends RxAppCompatActivity {

    //引用V层和P层
    private P presenter;
    private V view;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.setContext(this);
            presenter.bindLifecycle(bindToLifecycle());
            //bindUntilEvent(ActivityEvent.PAUSE);//绑定到Activity的pause生命周期（在pause销毁请求）
            //bindToLifecycle();//绑定activity，与activity生命周期一样
            presenter.attachView(view);
        }
        init();
    }

    //由子类指定具体类型
    public abstract int getLayoutId();

    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
        if (presenter != null) {
            presenter.detachView();
            presenter.detachContext();
        }
    }


    /**
     * [页面跳转]
     */
    protected void openActivity(Class<?> clz) {
        openActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     */
    protected void openActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Request permissions.
     */
    protected void requestPermission(String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        onRequestPermissionGranted(permissions);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        onRequestPermissionDenied(permissions);
                    }
                })
                .start();
    }

    /**
     * 申请权限成功
     */
    protected void onRequestPermissionGranted(@NonNull List<String> permissions) {

    }

    /**
     * 申请权限失败
     */
    protected void onRequestPermissionDenied(@NonNull List<String> permissions) {

    }
}
