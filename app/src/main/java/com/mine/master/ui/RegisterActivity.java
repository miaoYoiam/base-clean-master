package com.mine.master.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mine.domain.result.UserRegisterResult;
import com.mine.master.R;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.base.view.MvpBaseActivity;
import com.mine.master.ui.presenter.RegisterPresenter;
import com.mine.master.ui.view.RegisterView;
import com.mine.master.utils.Keys;
import com.mine.master.utils.Logger;
import com.mine.master.utils.SharePersistentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends MvpBaseActivity<RegisterView, RegisterPresenter> implements HasComponent<MineComponent>, RegisterView {
    @Inject
    RegisterPresenter presenter;
    private MineComponent mineComponent;

    @BindView(R.id.edit_username)
    EditText editUserName;

    @BindView(R.id.edit_password)
    EditText editUserPassword;

    @BindView(R.id.confirm_edit_password)
    EditText confirmEditUserPassword;

    @BindView(R.id.register_btn)
    TextView registerBtn;

    @BindView(R.id.user_role_select_container)
    RadioGroup userRoleRadioGroup;


    public static final int TYPE_ROLE_PERSONAL = 0;
    public static final int TYPE_ROLE_COMPANY = TYPE_ROLE_PERSONAL + 1;
    public int currentRoleType = TYPE_ROLE_PERSONAL;


    @Override
    public RegisterPresenter getPresenter() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getPresenter().attachView(this);
        getSupportActionBar().setTitle("");
        initUI();
    }

    private void initUI() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editUserName.getText().toString().trim();
                String userPassword = editUserPassword.getText().toString().trim();
                String confirmPassword = confirmEditUserPassword.getText().toString().trim();
                if (checkRegister(userName, userPassword, confirmPassword)) {
                    getPresenter().userRegister(userName, userPassword, currentRoleType + "");
                }
            }
        });
        userRoleRadioGroup.check(R.id.select_personal);
        userRoleRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.select_personal:
                        currentRoleType = TYPE_ROLE_PERSONAL;
                        break;
                    case R.id.select_company:
                        currentRoleType = TYPE_ROLE_COMPANY;
                        break;
                }
            }
        });
    }

    private boolean checkRegister(String userName, String userPassword, String confirmPassword) {
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!confirmPassword.equals(userPassword)) {
            Toast.makeText(RegisterActivity.this, "两次密码不同，请重试", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public MineComponent getComponent() {
        return this.mineComponent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_info, menu);
        MenuItem menuItem = menu.findItem(R.id.action_todo);
        menuItem.setTitle("登录");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item != null && item.getItemId() == R.id.action_todo) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        } catch (Exception e) {
            Logger.w(e.toString());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void userRegisterSuccess(UserRegisterResult userRegisterResult) {
        SharePersistentUtils.getInstance().saveInt(RegisterActivity.this, Keys.USER_ID, userRegisterResult.getUser().getId());
        if (!TextUtils.isEmpty(userRegisterResult.getUser().getRoleType())) {
            int roleType = 0;
            try {
                roleType = Integer.parseInt(userRegisterResult.getUser().getRoleType());
            } catch (Exception e) {
                Logger.w(e.toString());
            }
            SharePersistentUtils.getInstance().saveInt(RegisterActivity.this, Keys.USER_ROLE_TYPE, roleType);

        }
        SharePersistentUtils.getInstance().savePerference(RegisterActivity.this, Keys.USER_NAME, userRegisterResult.getUser().getUserName());
        SharePersistentUtils.getInstance().savePerference(RegisterActivity.this, Keys.USER_ROLE, userRegisterResult.getUser().getRoleName());
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void userRegisterFail(Throwable throwable) {
        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
    }
}
