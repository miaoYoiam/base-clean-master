package com.mine.master.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.R;
import com.mine.master.ui.base.view.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mine.master.utils.Keys.KEY_DETAIL_ENTIY;

public class DetailActivity extends BaseActivity {
    @BindView(R.id.personal_container)
    ScrollView personalContainer;
    @BindView(R.id.personal_title)
    TextView personalTitle;
    @BindView(R.id.personal_author)
    TextView personalAuthor;
    @BindView(R.id.personal_age)
    TextView personalAge;
    @BindView(R.id.personal_location)
    TextView personalLocation;
    @BindView(R.id.personal_sex)
    TextView personalSex;
    @BindView(R.id.personal_phone)
    TextView personalPhone;
    @BindView(R.id.personal_expect_salary)
    TextView personalExpectSalary;
    @BindView(R.id.personal_work_year)
    TextView personalWorkYear;
    @BindView(R.id.personal_work_experience)
    TextView personalWorkExperience;
    @BindView(R.id.personal_self_evaluation)
    TextView personalSelfEvaluation;
    @BindView(R.id.personal_professional_skill)
    TextView personalProfessionalSkill;
    //

    @BindView(R.id.company_container)
    ScrollView companyContainer;
    @BindView(R.id.company_title)
    TextView companyTitle;
    @BindView(R.id.company_location)
    TextView companyLocation;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.company_expect_salary)
    TextView companyExpectSalary;
    @BindView(R.id.company_phone)
    TextView companyPhone;
    @BindView(R.id.company_work_years)
    TextView companyWorkYears;
    @BindView(R.id.company_work_experience)
    TextView companyWorkExperience;
    @BindView(R.id.company_work_describe)
    TextView companyWorkDescribe;
    @BindView(R.id.company_describe)
    TextView companyDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bundle")) {
            Bundle bundle = intent.getBundleExtra("bundle");
            Serializable object = bundle.getSerializable(KEY_DETAIL_ENTIY);
            if (object instanceof CompanyInviteInfoListReuslt.ListBean) {
                initCompanyInfoData((CompanyInviteInfoListReuslt.ListBean) object);
            } else if (object instanceof PersonalResumeListResult.PersonalResumeBean) {
                initPersonalResumeData((PersonalResumeListResult.PersonalResumeBean) object);
            }
        }
    }

    private void initPersonalResumeData(PersonalResumeListResult.PersonalResumeBean data) {
        personalContainer.setVisibility(View.VISIBLE);
        personalTitle.setText(data.getTitle());
        personalAuthor.setText(data.getAuthor());
        personalSex.setText(data.getSex());
        personalPhone.setText(data.getPhone());
        personalExpectSalary.setText(data.getExpectSalary());
        personalLocation.setText(data.getLocation());
        personalWorkYear.setText(data.getWorkYears());
        personalWorkExperience.setText(data.getWorkExperience());
        personalSelfEvaluation.setText(data.getSelfEvaluation());
        personalProfessionalSkill.setText(data.getProfessionalSkill());
    }

    private void initCompanyInfoData(CompanyInviteInfoListReuslt.ListBean data) {
        companyContainer.setVisibility(View.VISIBLE);
        companyTitle.setText(data.getTitle());
        companyLocation.setText(data.getLocation());
        companyName.setText(data.getCompanyName());
        companyExpectSalary.setText(data.getExpectSalary());
        companyPhone.setText(data.getPhone());
        companyWorkYears.setText(data.getWorkYears());
        companyWorkExperience.setText(data.getWorkExperience());
        companyWorkDescribe.setText(data.getJobDescribe());
        companyDescribe.setText(data.getCompanyDescribe());
    }
}
