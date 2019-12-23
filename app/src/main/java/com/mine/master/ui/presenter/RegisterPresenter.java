package com.mine.master.ui.presenter;

import com.mine.domain.interactor.user.UserRegisterUseCase;
import com.mine.domain.repository.params.UserRegisterParams;
import com.mine.domain.result.UserRegisterResult;
import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.RegisterView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class RegisterPresenter extends MvpBasePresenter<RegisterView> {
    private static final String TAG = RegisterPresenter.class.getSimpleName();

    private UserRegisterUseCase userRegisterUseCase;

    private RegisterView registerView;

    @Inject
    public RegisterPresenter(UserRegisterUseCase userRegisterUseCase) {
        this.userRegisterUseCase = userRegisterUseCase;
    }


    public void userRegister(String userName, String userPassword) {
        UserRegisterParams UserRegisterParams = new UserRegisterParams();
        UserRegisterParams.userName = userName;
        UserRegisterParams.password = userPassword;
        UserRegisterParams.roleName = "普通用户";
        userRegisterUseCase.setParams(UserRegisterParams);
        this.userRegisterUseCase.execute(new UserRegisterUseCaseObserver(), UserRegisterParams);
    }

    private class UserRegisterUseCaseObserver extends DisposableObserver<UserRegisterResult> {


        @Override
        public void onNext(UserRegisterResult userRegisterResult) {
            if (registerView != null) {
                registerView.userRegisterSuccess(userRegisterResult);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (registerView != null) {
                registerView.userRegisterFail(e);
            }
        }

        @Override
        public void onComplete() {

        }
    }

    @Override
    public void attachView(RegisterView registerView) {
        super.attachView(registerView);
        this.registerView = registerView;
    }
}
