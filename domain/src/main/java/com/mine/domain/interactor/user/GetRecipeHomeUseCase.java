package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.model.GetRecipeHomeResult;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.GetRecipeHomeParams;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetRecipeHomeUseCase extends BaseRequestUseCase<GetRecipeHomeResult, GetRecipeHomeParams> {
    private UserRepository userRepository;

    @Inject
    public GetRecipeHomeUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;

    }

    @Override
    public Observable<GetRecipeHomeResult> buildUseCaseObservable(GetRecipeHomeParams getRecipeHomeParams) {
        return this.userRepository.getRecipeHome(getRecipeHomeParams);
    }
}
