package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.UploadPersonalResumeParams;
import com.mine.domain.result.UploadPersonalResumeResult;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UploadPersonalResumeUseCase extends BaseRequestUseCase<UploadPersonalResumeResult, UploadPersonalResumeParams>{
    private UserRepository userRepository;

    @Inject
    public UploadPersonalResumeUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<UploadPersonalResumeResult> buildUseCaseObservable(UploadPersonalResumeParams uploadPersonalResumeParams) {
        return this.userRepository.uploadPersonalResume(uploadPersonalResumeParams);
    }

}
