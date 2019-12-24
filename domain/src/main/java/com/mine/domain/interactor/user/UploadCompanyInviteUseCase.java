package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.UploadCompanyInviteParams;
import com.mine.domain.result.UploadCompanyInviteResult;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UploadCompanyInviteUseCase extends BaseRequestUseCase<UploadCompanyInviteResult, UploadCompanyInviteParams> {
    private UserRepository userRepository;

    @Inject
    public UploadCompanyInviteUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<UploadCompanyInviteResult> buildUseCaseObservable(UploadCompanyInviteParams uploadCompanyInviteParams) {
        return this.userRepository.uploadCompanyInvite(uploadCompanyInviteParams);
    }
}
