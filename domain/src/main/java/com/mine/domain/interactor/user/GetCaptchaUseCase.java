package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.model.GetCaptheResult;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.GetCaptchaParams;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCaptchaUseCase extends BaseRequestUseCase<GetCaptheResult, GetCaptchaParams> {
    private GetCaptchaParams getCaptchaParams;
    private UserRepository userRepository;

    @Inject
    public GetCaptchaUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;

    }

    @Override
    public Observable<GetCaptheResult> buildUseCaseObservable(GetCaptchaParams getCaptchaParams) {
        return this.userRepository.getCaptcha(getCaptchaParams);
    }

    public void setParams(GetCaptchaParams getCaptchaParams) {
        this.getCaptchaParams = getCaptchaParams;
    }

}
