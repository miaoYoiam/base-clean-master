package com.mine.master.internal.modules;

import android.app.Activity;

import com.mine.master.internal.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    Activity activity() {
        return this.activity;
    }
}
