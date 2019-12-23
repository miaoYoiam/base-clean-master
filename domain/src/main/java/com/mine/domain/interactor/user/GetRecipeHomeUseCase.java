package com.mine.domain.interactor.user;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.BaseRequestUseCase;
import com.mine.domain.result.GetRecipeHomeResult;
import com.mine.domain.repository.UserRepository;
import com.mine.domain.repository.params.GetRecipeHomeParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class GetRecipeHomeUseCase extends BaseRequestUseCase<GetRecipeHomeResult, GetRecipeHomeParams> {
    private UserRepository userRepository;
    private String offset;
    private String num;

    @Inject
    public GetRecipeHomeUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;

    }

    @Override
    public Observable<GetRecipeHomeResult> buildUseCaseObservable(GetRecipeHomeParams getRecipeHomeParams) {
        return this.userRepository.getRecipeHome(getRecipeHomeParams,offset,num);
    }

    public void execute(DisposableObserver<GetRecipeHomeResult> observer, GetRecipeHomeParams getRecipeHomeParams, String offset, String num) {
        this.offset = offset;
        this.num = num;
        super.execute(observer, getRecipeHomeParams);
    }
}
