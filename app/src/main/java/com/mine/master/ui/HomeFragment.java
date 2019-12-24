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
import com.mine.master.utils.Keys;
import com.mine.master.utils.Logger;
import com.mine.master.utils.SharePersistentUtils;
import com.mine.master.widget.BaseRecyclerView;
import com.mine.master.widget.BaseRecyclerViewAdapter;

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
        int userRoleValue = SharePersistentUtils.getInstance().getInt(App.app, Keys.USER_ROLE_TYPE, 0);
        if (userRoleValue == RegisterActivity.TYPE_ROLE_COMPANY) {
            requestPersonalResumeData();
        } else {
            requestCompanyResumeData();
        }
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
        recyclerView.setOnItemClickListener((view1, position) -> {
            Logger.e(">>>>>>>>>>>");
        });
    }


    public class DataAdapter extends BaseRecyclerViewAdapter {
        public DataAdapter(BaseActivity context) {
            super(context);
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
        if (companyInviteInfoListReuslt != null
                && companyInviteInfoListReuslt.getList() != null
                && !companyInviteInfoListReuslt.getList().isEmpty()) {
            for (CompanyInviteInfoListReuslt.ListBean companyBean : companyInviteInfoListReuslt.getList()) {
                adapter.addElements(companyBean, adapter.TYPE_COMPANY);
            }
            adapter.notifyDataSetChanged();
        }
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
