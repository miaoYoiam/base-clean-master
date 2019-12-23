package com.mine.master.ui.presenter;

import com.mine.domain.interactor.user.UserLoginUseCase;
import com.mine.domain.repository.params.UserLoginParams;
import com.mine.domain.result.UserLoginResult;
import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.LoginView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class LoginPresenter extends MvpBasePresenter<LoginView> {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    private UserLoginUseCase userLoginUseCase;

    private LoginView loginView;

    @Inject
    public LoginPresenter(UserLoginUseCase userLoginUseCase) {
        this.userLoginUseCase = userLoginUseCase;
    }

    public void userLogin(String userName, String userPassword) {
        UserLoginParams userLoginParams = new UserLoginParams();
        userLoginParams.userName = userName;
        userLoginParams.userPassword = userPassword;
        userLoginUseCase.setParams(userLoginParams);
        this.userLoginUseCase.execute(new UserLoginUseCaseObserver(), userLoginParams);
    }

    private class UserLoginUseCaseObserver extends DisposableObserver<UserLoginResult> {

        @Override
        public void onNext(UserLoginResult userLoginResult) {
            if (loginView != null) {
                loginView.userLoginSuccess(userLoginResult);
            }
        }

        @Override
        public void onError(Throwable t) {
            if (loginView != null) {
                loginView.userLoginFail(t);
            }
        }

        @Override
        public void onComplete() {
        }
    }

    @Override
    public void attachView(LoginView loginView) {
        super.attachView(loginView);
        this.loginView = loginView;
    }
}
