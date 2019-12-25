package com.mine.master.ui.presenter;

import com.mine.domain.interactor.user.UserRegisterUseCase;
import com.mine.domain.repository.params.UserRegisterParams;
import com.mine.domain.result.UserRegisterResult;
import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.MineFragmentView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class MineFragmentPresenter extends MvpBasePresenter<MineFragmentView> {

    private UserRegisterUseCase userRegisterUseCase;
    private MineFragmentView mineFragmentView;

    @Inject
    public MineFragmentPresenter(UserRegisterUseCase userRegisterUseCase) {
        this.userRegisterUseCase = userRegisterUseCase;
    }

    public void userRegister(String userName, Integer id) {
        UserRegisterParams params = new UserRegisterParams();
        params.userName = userName;
        params.id = id;
        userRegisterUseCase.setParams(params);
        this.userRegisterUseCase.execute(new UserUpdateUseCaseObserver(), params);
    }

    private class UserUpdateUseCaseObserver extends DisposableObserver<UserRegisterResult> {


        @Override
        public void onNext(UserRegisterResult userRegisterResult) {
            if (mineFragmentView != null) {
                mineFragmentView.userUpdateSuccess(userRegisterResult);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (mineFragmentView != null) {
                mineFragmentView.userUpdateFail(e);
            }
        }

        @Override
        public void onComplete() {

        }
    }

    @Override
    public void attachView(MineFragmentView mineFragmentView) {
        super.attachView(mineFragmentView);
        this.mineFragmentView = mineFragmentView;
    }
}
