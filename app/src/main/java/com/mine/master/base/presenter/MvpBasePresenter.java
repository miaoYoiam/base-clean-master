package com.mine.master.base.presenter;

import com.mine.master.base.view.MvpView;

import java.lang.ref.WeakReference;

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> viewRef;

    public void attachView(V v) {
        this.viewRef = new WeakReference<>(v);
    }

    public void destroy() {

    }

    public void detachView(boolean retainInstance) {
        if (this.viewRef != null) {
            this.viewRef.clear();
            this.viewRef = null;
        }
    }

    public V getView() {
        return null;
    }

    public void initialize() {

    }

    public void pause() {

    }

    public void resume() {

    }
}
