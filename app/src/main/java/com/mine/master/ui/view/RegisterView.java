package com.mine.master.ui.view;

import com.mine.domain.result.UserRegisterResult;
import com.mine.master.ui.base.view.MvpView;

public interface RegisterView extends MvpView {
    void userRegisterSuccess(UserRegisterResult userRegisterResult);

    void userRegisterFail(Throwable throwable);
}
