package com.mine.master.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mine.master.R;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.ui.base.view.MvpBaseFragment;
import com.mine.master.ui.presenter.MineFragmentPresenter;
import com.mine.master.ui.view.MineFragmentView;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends MvpBaseFragment<MineFragmentView, MineFragmentPresenter> implements MineFragmentView {
    @Inject
    MineFragmentPresenter presenter;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MineComponent.class).inject(this);
        getPresenter().attachView(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_mine, container, false);
    }

    @Override
    protected MineFragmentPresenter getPresenter() {
        return this.presenter;
    }


}
