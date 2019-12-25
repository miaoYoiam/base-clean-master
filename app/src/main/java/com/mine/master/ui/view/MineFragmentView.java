package com.mine.master.ui.view;

import com.mine.domain.result.UserRegisterResult;
import com.mine.master.ui.base.view.MvpView;

public interface MineFragmentView extends MvpView {
    void userUpdateSuccess(UserRegisterResult userRegisterResult);

    void userUpdateFail(Throwable throwable);
}
