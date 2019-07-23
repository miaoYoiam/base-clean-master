package com.mine.master.ui;

import android.os.Bundle;

import com.mine.master.R;
import com.mine.master.base.view.MvpBaseActivity;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.presenter.TestPresenter;
import com.mine.master.view.TestView;

import javax.inject.Inject;

public class TestActivity extends MvpBaseActivity<TestView, TestPresenter> implements HasComponent<MineComponent>, TestView {
    @Inject
    TestPresenter presenter;
    private MineComponent mineComponent;

    @Override
    public TestPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void injectMembers() {
        DaggerMineComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mineModule(new MineModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_test);
        getData();
    }

    private void getData() {
        presenter.getRecipeHome(13,0,20);
    }

    @Override
    public MineComponent getComponent() {
        return this.mineComponent;
    }
}
