package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.PersonalResumeListParams;
import com.mine.domain.result.PersonalResumeListResult;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PersonalResumeListUseCase extends BaseRequestUseCase<PersonalResumeListResult, PersonalResumeListParams> {
    private UserRepository userRepository;

    public PersonalResumeListParams personalResumeListParams;
    @Inject
    public PersonalResumeListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<PersonalResumeListResult> buildUseCaseObservable(PersonalResumeListParams personalResumeListParams) {
        return this.userRepository.getPersonalResumeListData(personalResumeListParams);
    }
}
