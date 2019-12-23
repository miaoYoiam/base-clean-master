package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.UserRegisterParams;
import com.mine.domain.result.UserRegisterResult;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRegisterUseCase extends BaseRequestUseCase<UserRegisterResult, UserRegisterParams> {

    private UserRepository userRepository;
    private UserRegisterParams userRegisterParams;

    @Inject
    public UserRegisterUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<UserRegisterResult> buildUseCaseObservable(UserRegisterParams userRegisterParams) {
        return this.userRepository.userRegister(userRegisterParams);
    }


    public void setParams(UserRegisterParams userRegisterParams) {
        this.userRegisterParams = userRegisterParams;
    }
}
