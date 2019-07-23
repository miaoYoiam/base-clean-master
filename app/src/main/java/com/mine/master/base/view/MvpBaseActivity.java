package com.mine.master.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mine.master.base.presenter.MvpPresenter;

public abstract class MvpBaseActivity<V extends MvpView, P extends MvpPresenter> extends BaseActivity implements MvpView {
    public abstract P getPresenter();

    public abstract void injectMembers();

    public Context context() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectMembers();
        getPresenter().attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView(false);
        getPresenter().destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

}
