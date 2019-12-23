package com.mine.master.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mine.domain.result.UserLoginResult;
import com.mine.master.R;
import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.DaggerMineComponent;
import com.mine.master.internal.components.MineComponent;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.base.view.MvpBaseActivity;
import com.mine.master.ui.presenter.LoginPresenter;
import com.mine.master.ui.view.LoginView;
import com.mine.master.utils.Keys;
import com.mine.master.utils.Logger;
import com.mine.master.utils.SharePersistentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends MvpBaseActivity<LoginView, LoginPresenter> implements HasComponent<MineComponent>, LoginView {
    @Inject
    LoginPresenter presenter;
    private MineComponent mineComponent;

    @BindView(R.id.edit_username)
    EditText editUserName;

    @BindView(R.id.edit_password)
    EditText editUserPassword;

    @BindView(R.id.login_btn)
    TextView loginBtn;

    @Override
    public LoginPresenter getPresenter() {
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
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getPresenter().attachView(this);
        getSupportActionBar().setTitle("");
        initUI();
    }

    private void initUI() {
        loginBtn.setOnClickListener(v -> {
//            String userName = editUserName.getText().toString().trim();
//            String userPassword = editUserPassword.getText().toString().trim();
//            if (checkLogin(userName, userPassword)) {
//                getPresenter().userLogin(userName, userPassword);
//            }
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();

        });
    }

    private boolean checkLogin(String userName, String userPassword) {
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public MineComponent getComponent() {
        return this.mineComponent;
    }

    @Override
    public void userLoginSuccess(UserLoginResult userLoginResult) {
        SharePersistentUtils.getInstance().saveInt(LoginActivity.this, Keys.USER_ID, userLoginResult.id);
        SharePersistentUtils.getInstance().savePerference(LoginActivity.this, Keys.USER_NAME, userLoginResult.userName);
        SharePersistentUtils.getInstance().savePerference(LoginActivity.this, Keys.USER_PASSWORD, userLoginResult.password);
        SharePersistentUtils.getInstance().savePerference(LoginActivity.this, Keys.USER_ROLE, userLoginResult.roleName);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void userLoginFail(Throwable throwable) {
        Toast.makeText(this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_info, menu);
        MenuItem menuItem = menu.findItem(R.id.action_todo);
        menuItem.setTitle("注册");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item != null && item.getItemId() == R.id.action_todo) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        } catch (Exception e) {
            Logger.w(e.toString());
        }
        return super.onOptionsItemSelected(item);
    }
}
