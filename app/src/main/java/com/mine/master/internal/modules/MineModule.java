package com.mine.master.internal.modules;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.interactor.user.GetCaptchaUseCase;
import com.mine.domain.interactor.user.UserLoginUseCase;
import com.mine.domain.interactor.user.UserRegisterUseCase;
import com.mine.domain.repository.UserRepository;
import com.mine.master.internal.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MineModule {

    public MineModule() {
    }

    @PerActivity
    @Provides
    GetCaptchaUseCase provideGetCaptcha(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetCaptchaUseCase(userRepository, threadExecutor, postExecutionThread);
    }


    @PerActivity
    @Provides
    UserLoginUseCase provideUserLoginUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new UserLoginUseCase(userRepository, threadExecutor, postExecutionThread);
    }


    @PerActivity
    @Provides
    UserRegisterUseCase provideUserRegisterUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new UserRegisterUseCase(threadExecutor, postExecutionThread, userRepository);
    }
}
