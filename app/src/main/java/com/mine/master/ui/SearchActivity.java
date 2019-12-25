package com.mine.master.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.App;
import com.mine.master.R;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.base.view.BaseActivity;
import com.mine.master.ui.base.view.MvpBaseActivity;
import com.mine.master.ui.presenter.SearchPresenter;
import com.mine.master.ui.view.SearchView;
import com.mine.master.utils.Keys;
import com.mine.master.utils.SharePersistentUtils;
import com.mine.master.widget.BaseRecyclerView;
import com.mine.master.widget.BaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mine.master.utils.Keys.KEY_DETAIL_ENTIY;

public class SearchActivity extends MvpBaseActivity<SearchView, SearchPresenter> implements HasComponent<MineComponent>, SearchView {
    @Inject
    SearchPresenter presenter;
    MineComponent mineComponent;

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.search_text)
    EditText searchEdit;
    @BindView(R.id.search_button)
    FrameLayout searchButton;
    @BindView(R.id.recyclerview)
    BaseRecyclerView recyclerview;


    private SearchAdapter adapter;

    @Override
    public SearchPresenter getPresenter() {
        return presenter;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getPresenter().attachView(this);
        initUI();
    }

    private void initUI() {
        back.setOnClickListener(v -> finish());
        adapter = new SearchAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(manager);
        recyclerview.setOnItemClickListener((view, position) -> {
            int viewType = adapter.getItemViewType(position);
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            Bundle bundle = new Bundle();
            if (viewType == adapter.TYPE_COMPANY) {
                bundle.putSerializable(KEY_DETAIL_ENTIY, (CompanyInviteInfoListReuslt.ListBean) adapter.itemList.get(position));
            } else if (viewType == adapter.TYPE_PERSONAL) {
                bundle.putSerializable(KEY_DETAIL_ENTIY, (PersonalResumeListResult.PersonalResumeBean) adapter.itemList.get(position));
            }
            intent.putExtra("bundle",bundle);
            startActivity(intent);
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchEdit.getText().toString().trim();
                if (TextUtils.isEmpty(keyword)) {
                    Toast.makeText(SearchActivity.this, "请输入搜索词", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestData(keyword);
            }
        });
    }

    private void requestData(String keyword) {
        int userRoleValue = SharePersistentUtils.getInstance().getInt(App.app, Keys.USER_ROLE_TYPE, 0);
        if (userRoleValue == RegisterActivity.TYPE_ROLE_COMPANY) {
            requestPersonalResumeData(keyword);
        } else {
            requestCompanyResumeData(keyword);
        }
    }


    private void requestPersonalResumeData(String keyword) {
        getPresenter().getPersonalResumeListData(1, 50, keyword);
    }

    private void requestCompanyResumeData(String keyword) {
        getPresenter().getCompanyInviteInfoListData(1, 50, keyword);
    }


    @Override
    public MineComponent getComponent() {
        return mineComponent;
    }

    @Override
    public void getPersonalResumeListDataSuccess(PersonalResumeListResult personalResumeListResult) {
        adapter.clearData();
        if (personalResumeListResult != null
                && personalResumeListResult.getList() != null
                && !personalResumeListResult.getList().isEmpty()) {
            for (PersonalResumeListResult.PersonalResumeBean personalResumeBean : personalResumeListResult.getList()) {
                adapter.addElements(personalResumeBean, adapter.TYPE_PERSONAL);
            }
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "未找到匹配的信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getPersonalResumeListDataFail(Throwable e) {
        Toast.makeText(this, "未找到匹配的信息", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCompanyInviteInfoListDataSuccess(CompanyInviteInfoListReuslt companyInviteInfoListReuslt) {
        adapter.clearData();
        if (companyInviteInfoListReuslt != null
                && companyInviteInfoListReuslt.getList() != null
                && !companyInviteInfoListReuslt.getList().isEmpty()) {
            for (CompanyInviteInfoListReuslt.ListBean companyBean : companyInviteInfoListReuslt.getList()) {
                adapter.addElements(companyBean, adapter.TYPE_COMPANY);
            }
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "未找到匹配的信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCompanyInviteInfoListDataFail(Throwable e) {
        Toast.makeText(this, "未找到匹配的信息", Toast.LENGTH_SHORT).show();
    }

    public class SearchAdapter extends BaseRecyclerViewAdapter {
        public SearchAdapter(BaseActivity context) {
            super(context);
        }
    }
}
