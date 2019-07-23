package com.mine.master.internal.components;

import com.mine.master.MainActivity;
import com.mine.master.internal.PerActivity;
import com.mine.master.internal.modules.ActivityModule;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.TestActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, MineModule.class})
public interface MineComponent extends ActivityComponent{
    void inject(MainActivity mainActivity);


    void inject(TestActivity testActivity);

}
