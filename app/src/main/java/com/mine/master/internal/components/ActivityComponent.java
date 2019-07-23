package com.mine.master.internal.components;

import android.app.Activity;

import com.mine.master.internal.PerActivity;
import com.mine.master.internal.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity activity();
}

