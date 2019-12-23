package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.CompanyInviteInfoListParams;
import com.mine.domain.result.CompanyInviteInfoListReuslt;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CompanyInviteInfoListUseCase extends BaseRequestUseCase<CompanyInviteInfoListReuslt, CompanyInviteInfoListParams> {
    private UserRepository userRepository;
    public CompanyInviteInfoListParams companyInviteInfoListParams;

    @Inject
    public CompanyInviteInfoListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }


    @Override
    public Observable<CompanyInviteInfoListReuslt> buildUseCaseObservable(CompanyInviteInfoListParams companyInviteInfoListParams) {
        return this.userRepository.getCompanyInviteInfoListData(companyInviteInfoListParams);
    }
}
