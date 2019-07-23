package com.mine.master;

import android.app.Application;

import com.mine.master.internal.components.ApplicationComponent;
import com.mine.master.internal.components.DaggerApplicationComponent;
import com.mine.master.internal.modules.ApplicationModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
