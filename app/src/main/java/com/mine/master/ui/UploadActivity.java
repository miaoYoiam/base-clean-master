package com.mine.master.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.mine.domain.result.UploadCompanyInviteResult;
import com.mine.domain.result.UploadPersonalResumeResult;
import com.mine.master.App;
import com.mine.master.R;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.base.view.MvpBaseActivity;
import com.mine.master.ui.presenter.UploadPresenter;
import com.mine.master.ui.view.UploadView;
import com.mine.master.utils.Keys;
import com.mine.master.utils.Logger;
import com.mine.master.utils.SharePersistentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadActivity extends MvpBaseActivity<UploadView, UploadPresenter> implements HasComponent<MineComponent>, UploadView {
    @Inject
    UploadPresenter presenter;
    private MineComponent mineComponent;

    @BindView(R.id.personal_container)
    ScrollView personalContainer;
    @BindView(R.id.personal_title)
    EditText personalTitle;
    @BindView(R.id.personal_author)
    EditText personalAuthor;
    @BindView(R.id.personal_age)
    EditText personalAge;
    @BindView(R.id.personal_location)
    EditText personalLocation;
    @BindView(R.id.personal_sex)
    EditText personalSex;
    @BindView(R.id.personal_phone)
    EditText personalPhone;
    @BindView(R.id.personal_expect_salary)
    EditText personalExpectSalary;
    @BindView(R.id.personal_work_year)
    EditText personalWorkYear;
    @BindView(R.id.personal_work_experience)
    EditText personalWorkExperience;
    @BindView(R.id.personal_self_evaluation)
    EditText personalSelfEvaluation;
    @BindView(R.id.personal_professional_skill)
    EditText personalProfessionalSkill;
    //

    @BindView(R.id.company_container)
    ScrollView companyContainer;
    @BindView(R.id.company_title)
    EditText companyTitle;
    @BindView(R.id.company_location)
    EditText companyLocation;
    @BindView(R.id.company_name)
    EditText companyName;
    @BindView(R.id.company_expect_salary)
    EditText companyExpectSalary;
    @BindView(R.id.company_phone)
    EditText companyPhone;
    @BindView(R.id.company_work_years)
    EditText companyWorkYears;
    @BindView(R.id.company_work_experience)
    EditText companyWorkExperience;
    @BindView(R.id.company_work_describe)
    EditText companyWorkDescribe;
    @BindView(R.id.company_describe)
    EditText companyDescribe;

    @Override
    public UploadPresenter getPresenter() {
        return this.presenter;
    }

    private int userRoleValue = RegisterActivity.TYPE_ROLE_COMPANY;

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
        setContentView(R.layout.activity_upload);
        getPresenter().attachView(this);
        getSupportActionBar().setTitle("编辑信息");
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        userRoleValue = SharePersistentUtils.getInstance().getInt(App.app, Keys.USER_ROLE_TYPE, 0);
        if (userRoleValue == RegisterActivity.TYPE_ROLE_COMPANY) {
            companyContainer.setVisibility(View.VISIBLE);
        } else {
            personalContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public MineComponent getComponent() {
        return this.mineComponent;
    }

    @Override
    public void uploadPersonalResumeSuccess(UploadPersonalResumeResult uploadPersonalResumeResult) {
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void uploadPersonalResumeFail(String message) {
        Toast.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadCompanyInviteFail(String message) {
        Toast.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadCompanyInviteSuccess(UploadCompanyInviteResult uploadCompanyInviteResult) {
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_info, menu);
        MenuItem menuItem = menu.findItem(R.id.action_todo);
        menuItem.setTitle("上传");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item != null && item.getItemId() == R.id.action_todo) {
                upload();
            }
        } catch (Exception e) {
            Logger.w(e.toString());
        }
        return super.onOptionsItemSelected(item);
    }

    private void upload() {
        if (userRoleValue == RegisterActivity.TYPE_ROLE_COMPANY) {
            companyUpload();
        } else {
            personalUpload();
        }
    }

    private void companyUpload() {
        String title = companyTitle.getText().toString().trim();
        String location = companyLocation.getText().toString().trim();
        String name = companyName.getText().toString().trim();
        String expectSalary = companyExpectSalary.getText().toString().trim();
        String phone = companyPhone.getText().toString().trim();
        String workYears = companyWorkYears.getText().toString().trim();
        String workExperience = companyWorkExperience.getText().toString().trim();
        String workDescribe = companyWorkDescribe.getText().toString().trim();
        String companyDescribeStr = companyDescribe.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(location)) {
            Toast.makeText(this, "请输入公司地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入公司名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(expectSalary)) {
            Toast.makeText(this, "请输入薪资范畴", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workYears)) {
            Toast.makeText(this, "请输入工作年限", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workExperience)) {
            Toast.makeText(this, "请输入工作经验", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workDescribe)) {
            Toast.makeText(this, "请输入工作描述", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(companyDescribeStr)) {
            Toast.makeText(this, "请输入公司简介", Toast.LENGTH_SHORT).show();
            return;
        }

        getPresenter().uploadCompanyInvite(title, location, name, expectSalary, phone, workYears, workExperience, workDescribe, companyDescribeStr);

    }

    private void personalUpload() {
        String title = personalTitle.getText().toString().trim();
        String authorName = personalAuthor.getText().toString().trim();
        String age = personalAge.getText().toString().trim();
        String sex = personalSex.getText().toString().trim();
        String phone = personalPhone.getText().toString().trim();
        String expectSalary = personalExpectSalary.getText().toString().trim();
        String location = personalLocation.getText().toString().trim();
        String workYear = personalWorkYear.getText().toString().trim();
        String workExperience = personalWorkExperience.getText().toString().trim();
        String selfEvaluation = personalSelfEvaluation.getText().toString().trim();
        String professionalSkill = personalProfessionalSkill.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(authorName)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(expectSalary)) {
            Toast.makeText(this, "请输入期望薪资", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(location)) {
            Toast.makeText(this, "请输入家庭住址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workYear)) {
            Toast.makeText(this, "请输入工作年限", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workExperience)) {
            Toast.makeText(this, "请输入工作经验", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(selfEvaluation)) {
            Toast.makeText(this, "请输入自我评价", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(professionalSkill)) {
            Toast.makeText(this, "请输入专业技能", Toast.LENGTH_SHORT).show();
            return;
        }

        getPresenter().uploadPersonalResume(title, authorName, age, sex, phone, expectSalary, location, workYear, workExperience, selfEvaluation, professionalSkill);
    }

}
