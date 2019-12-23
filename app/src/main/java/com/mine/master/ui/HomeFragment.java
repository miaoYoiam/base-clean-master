package com.mine.master.ui;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.App;
import com.mine.master.R;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.ui.base.view.BaseActivity;
import com.mine.master.ui.base.view.MvpBaseFragment;
import com.mine.master.ui.presenter.HomeFragmentPresenter;
import com.mine.master.ui.view.HomeFragmentView;
import com.mine.master.utils.AMapLocationUtils;
import com.mine.master.widget.BaseRecyclerView;
import com.mine.master.widget.BaseRecyclerViewAdapter;
import com.mine.master.widget.Holder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MvpBaseFragment<HomeFragmentView, HomeFragmentPresenter> implements HomeFragmentView {
    //key
    @Inject
    HomeFragmentPresenter presenter;

    private DataAdapter adapter;

    @BindView(R.id.recyclerview)
    BaseRecyclerView recyclerView;

    @BindView(R.id.search_container)
    LinearLayout searchContainer;

    @BindView(R.id.location)
    TextView location;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.f_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initAmapLocation();
        requestData();
        sHA1(getActivity());
    }

    private void initAmapLocation() {
        AMapLocationUtils.getInstance().getLocationClient().startLocation();
        AMapLocationUtils.getInstance().setListener(aMapLocation -> {
            if (location != null) {
                location.setText(aMapLocation.getCity());
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(App.app, aMapLocation.getAddress(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void requestData() {
        requestPersonalResumeData();
    }

    private void requestPersonalResumeData() {
        getPresenter().getPersonalResumeListData(1, 50);
    }

    private void requestCompanyResumeData() {
        getPresenter().getCompanyInviteInfoListData(1, 50);
    }

    private void initUI(View view) {
        adapter = new DataAdapter((BaseActivity) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }


    public class DataAdapter extends BaseRecyclerViewAdapter {
        public final int TYPE_PERSONAL = BaseRecyclerViewAdapter.typeCount + 1;
        public final int TYPE_COMPANY = TYPE_PERSONAL + 1;

        public DataAdapter(BaseActivity context) {
            super(context);
        }

        @Override
        public Holder extensionOnCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_PERSONAL) {
                return new PersonalResumeHolder(inflater.inflate(R.layout.item_personal_resume, parent, false));
            } else if (viewType == TYPE_COMPANY) {
                return new CompanyResumeHolder(inflater.inflate(R.layout.item_company_resume, parent, false));
            }
            return super.extensionOnCreateViewHolder(parent, viewType);
        }

        @Override
        public void extensionOnBindViewHolder(Holder holder, int position) {
            super.extensionOnBindViewHolder(holder, position);
            if (holder.getItemViewType() == TYPE_PERSONAL) {
                handlePersonalResumeData((PersonalResumeListResult.PersonalResumeBean) adapter.itemList.get(position), (PersonalResumeHolder) holder, position);
            } else if (holder.getItemViewType() == TYPE_COMPANY) {
                handleCompanyResumeData(holder, position);
            }
        }

        public class PersonalResumeHolder extends Holder {
            @BindView(R.id.title)
            TextView title;
            @BindView(R.id.expect_salary)
            TextView expectSalary;
            @BindView(R.id.personal_name)
            TextView personalName;
            @BindView(R.id.work_years)
            TextView workYears;
            @BindView(R.id.create_date)
            TextView createDate;
            @BindView(R.id.phone)
            TextView phone;

            public PersonalResumeHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }


        public class CompanyResumeHolder extends Holder {
            @BindView(R.id.title)
            TextView title;
            @BindView(R.id.expect_salary)
            TextView expectSalary;
            @BindView(R.id.company_name)
            TextView companyName;
            @BindView(R.id.work_years)
            TextView workYears;
            @BindView(R.id.create_date)
            TextView createDate;
            @BindView(R.id.phone)
            TextView phone;

            public CompanyResumeHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }

    private void handleCompanyResumeData(Holder holder, int position) {
    }

    private void handlePersonalResumeData(PersonalResumeListResult.PersonalResumeBean data, DataAdapter.PersonalResumeHolder holder, int position) {
        if (data != null && holder != null) {
            holder.title.setText(data.getTitle());
            holder.expectSalary.setText(data.getExpectSalary());
            holder.personalName.setText(data.getAuthor());
            holder.workYears.setText(data.getWorkYears());
            holder.createDate.setText(data.getCreateDate());
            holder.phone.setText("联系电话：" + data.getPhone());
        }
    }

    @Override
    protected HomeFragmentPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void getPersonalResumeListDataSuccess(PersonalResumeListResult personalResumeListResult) {
        if (personalResumeListResult != null
                && personalResumeListResult.getList() != null
                && !personalResumeListResult.getList().isEmpty()) {
            for (PersonalResumeListResult.PersonalResumeBean personalResumeBean : personalResumeListResult.getList()) {
                adapter.addElements(personalResumeBean, adapter.TYPE_PERSONAL);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getPersonalResumeListDataFail(Throwable e) {

    }

    @Override
    public void getCompanyInviteInfoListDataSuccess(CompanyInviteInfoListReuslt companyInviteInfoListReuslt) {

    }

    @Override
    public void getCompanyInviteInfoListDataFail(Throwable e) {

    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
