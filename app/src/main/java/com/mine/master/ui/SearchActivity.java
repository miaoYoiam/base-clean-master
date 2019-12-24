package com.mine.master.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mine.master.R;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.base.view.BaseActivity;
import com.mine.master.ui.base.view.MvpBaseActivity;
import com.mine.master.ui.presenter.SearchPresenter;
import com.mine.master.ui.view.SearchView;
import com.mine.master.widget.BaseRecyclerView;
import com.mine.master.widget.BaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    }

    @Override
    public MineComponent getComponent() {
        return mineComponent;
    }

    public class SearchAdapter extends BaseRecyclerViewAdapter {
        public SearchAdapter(BaseActivity context) {
            super(context);
        }
    }
}
