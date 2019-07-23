package com.mine.master.internal.modules;

import com.mine.data.net.HttpService;
import com.mine.data.net.api.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {
    private <T> T createApi(Class<T> classz) {
        return HttpService.getInstance().createApi(classz);
    }

    @Singleton
    @Provides
    Api getUserApi() {
        return createApi(Api.class);
    }
}
