package com.mine.master.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.mine.master.R;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.base.view.BaseFragment;
import com.mine.master.ui.base.view.MvpBaseActivity;
import com.mine.master.ui.presenter.HomePresenter;
import com.mine.master.ui.view.HomeView;
import com.mine.master.utils.Logger;
import com.mine.master.widget.BottomViewWidget;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends MvpBaseActivity<HomeView, HomePresenter> implements BottomViewWidget.BottomTabClickListener, HomeView, HasComponent<MineComponent> {
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.bottom_container)
    BottomViewWidget bottomContainer;
    private HashMap<String, BaseFragment> fragmentMap;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private final String[] fragmentTag = {"home", "mine"};
    private BaseFragment currentFragment;
    private int currentIndex = 0;

    @Inject
    HomePresenter presenter;

    private MineComponent mineComponent;

    @Override
    public HomePresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void injectMembers() {
        mineComponent = DaggerMineComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mineModule(new MineModule())
                .build();
        mineComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_home);
        ButterKnife.bind(this);
        getPresenter().attachView(this);
        initView();
        initFragments();
        showFragment(currentIndex);
    }

    private void initView() {
        bottomContainer.setTabClickListener(this);
    }

    private void initFragments() {
        fragmentMap = new HashMap<>();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();

        fragmentMap.put("home", homeFragment);
        fragmentMap.put("mine", mineFragment);
    }

    public void showFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentTag.length; i++) {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag[i]);
            if (fragment == null) {
                fragment = fragmentMap.get(fragmentTag[i]);
                transaction.add(R.id.fragment_container, fragment, fragmentTag[i]);
            }
            if (index == i) {
                currentIndex = i;
                currentFragment = fragment;
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
        }
        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.commitAllowingStateLoss();
            Logger.e(e.toString());
        }
    }

    @Override
    public void onClickTab(int currentTab) {
        showFragment(currentTab);
    }

    @Override
    public MineComponent getComponent() {
        return this.mineComponent;
    }
}
