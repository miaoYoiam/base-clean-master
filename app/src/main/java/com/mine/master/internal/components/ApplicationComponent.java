package com.mine.master.internal.components;

import android.content.Context;

import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.repository.UserRepository;
import com.mine.master.base.view.BaseActivity;
import com.mine.master.internal.modules.ApiModule;
import com.mine.master.internal.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    Context context();

    void inject(BaseActivity baseActivity);

    UserRepository userRepository();

    PostExecutionThread postExecutionThread();

    ThreadExecutor threadExecutor();
}
