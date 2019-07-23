package com.mine.master.internal.modules;

import android.content.Context;

import com.mine.data.executor.JobExecutor;
import com.mine.data.repository.UserDataRepository;
import com.mine.domain.executor.PostExecutionThread;
import com.mine.domain.executor.ThreadExecutor;
import com.mine.domain.repository.UserRepository;
import com.mine.master.App;
import com.mine.master.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideApplicationContext() {
        return this.application;
    }

    @Singleton
    @Provides
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Singleton
    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Singleton
    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
