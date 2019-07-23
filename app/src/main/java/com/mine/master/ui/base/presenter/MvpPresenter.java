package com.mine.master.ui.base.presenter;

import com.mine.master.ui.base.view.MvpView;

public interface MvpPresenter<V extends MvpView> {
    void attachView(V v);

    void destroy();

    void detachView(boolean retainInstance);

    V getView();

    void initialize();

    void pause();

    void resume();
}
