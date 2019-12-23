package com.mine.master.ui.presenter;

import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.HomeView;

import javax.inject.Inject;

public class HomePresenter extends MvpBasePresenter<HomeView> {
    private HomeView homeView;

    @Inject
    public HomePresenter() {
    }

    @Override
    public void attachView(HomeView homeView) {
        super.attachView(homeView);
        this.homeView = homeView;
    }
}
