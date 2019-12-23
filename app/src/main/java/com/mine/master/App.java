package com.mine.master;

import android.app.Application;

import com.mine.master.internal.components.ApplicationComponent;
import com.mine.master.internal.components.DaggerApplicationComponent;
import com.mine.master.internal.modules.ApplicationModule;
import com.mine.master.utils.AMapLocationUtils;

public class App extends Application {
    private ApplicationComponent applicationComponent;
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initInjector();
        AMapLocationUtils.getInstance().initAMap(app);
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
