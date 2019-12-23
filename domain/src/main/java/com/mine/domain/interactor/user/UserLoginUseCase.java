package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.UserLoginParams;
import com.mine.domain.result.UserLoginResult;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserLoginUseCase extends BaseRequestUseCase<UserLoginResult, UserLoginParams> {
    private UserRepository userRepository;
    private UserLoginParams userLoginParams;

    @Inject
    public UserLoginUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }


    @Override
    public Observable<UserLoginResult> buildUseCaseObservable(UserLoginParams userLoginParams) {
        return this.userRepository.userLogin(userLoginParams);
    }

    public void setParams(UserLoginParams userLoginParams) {
        this.userLoginParams = userLoginParams;
    }
}
