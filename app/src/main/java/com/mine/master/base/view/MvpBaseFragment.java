package com.mine.master.base.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.mine.master.base.presenter.MvpPresenter;

public abstract class MvpBaseFragment<V extends MvpView, P extends MvpPresenter> extends BaseFragment implements MvpView {
    protected abstract P getPresenter();

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().attachView((V) this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detachView(false);
    }

    public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
}
