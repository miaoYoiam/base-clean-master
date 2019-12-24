package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.SearchListParams;
import com.mine.domain.result.SearchListResult;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchListUseCase extends BaseRequestUseCase<SearchListResult, SearchListParams> {
    private UserRepository userRepository;

    @Inject
    public SearchListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = repository;
    }

    @Override
    public Observable<SearchListResult> buildUseCaseObservable(SearchListParams searchListParams) {
        return this.userRepository.searchListData(searchListParams);
    }
}
