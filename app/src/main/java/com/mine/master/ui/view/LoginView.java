package com.mine.master.ui.view;

import com.mine.domain.result.UserLoginResult;
import com.mine.master.ui.base.view.MvpView;

public interface LoginView extends MvpView {
    void userLoginSuccess(UserLoginResult userLoginResult);
    void userLoginFail(Throwable throwable);
}
