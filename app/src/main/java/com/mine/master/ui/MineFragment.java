package com.mine.master.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mine.master.App;
import com.mine.master.R;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.ui.base.view.MvpBaseFragment;
import com.mine.master.ui.presenter.MineFragmentPresenter;
import com.mine.master.ui.view.MineFragmentView;
import com.mine.master.utils.Keys;
import com.mine.master.utils.SharePersistentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends MvpBaseFragment<MineFragmentView, MineFragmentPresenter> implements MineFragmentView {
    @Inject
    MineFragmentPresenter presenter;

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_role)
    TextView userRole;
    @BindView(R.id.show_personal_resume)
    FrameLayout showPersonalResume;
    @BindView(R.id.show_company_resume)
    FrameLayout showCompanyResume;
    @BindView(R.id.show_advise)
    FrameLayout showAdvise;
    @BindView(R.id.show_treaty)
    FrameLayout showTreaty;
    @BindView(R.id.show_version)
    FrameLayout showVersion;

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
        View view = inflater.inflate(R.layout.f_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        String userNameValue = SharePersistentUtils.getInstance().getPerference(App.app, Keys.USER_NAME);
        userName.setText(userNameValue);
        int userRoleValue = SharePersistentUtils.getInstance().getInt(App.app, Keys.USER_ROLE_TYPE, 0);
        if (userRoleValue == RegisterActivity.TYPE_ROLE_COMPANY) {
            userRole.setText("企业用户");
            showCompanyResume.setVisibility(View.VISIBLE);
            showCompanyResume.setOnClickListener(v -> {
//                    todo 查看简历
            });
        } else {
            userRole.setText("个人用户");
            showPersonalResume.setVisibility(View.VISIBLE);
            showPersonalResume.setOnClickListener(v -> {
//                    todo 查看简历
            });
        }
        showAdvise.setOnClickListener(v -> {
            //        todo 意见反馈

        });
        showTreaty.setOnClickListener(v -> {
            //        todo 隐私条款

        });
        showVersion.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "已是最新版本1.0.0", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected MineFragmentPresenter getPresenter() {
        return this.presenter;
    }


}
