package com.mine.master.internal.components;

import com.mine.master.internal.PerActivity;
import com.mine.master.internal.modules.ActivityModule;
import com.mine.master.internal.modules.MineModule;
import com.mine.master.ui.HomeActivity;
import com.mine.master.ui.HomeFragment;
import com.mine.master.ui.LoginActivity;
import com.mine.master.ui.MainActivity;
import com.mine.master.ui.MineFragment;
import com.mine.master.ui.RegisterActivity;
import com.mine.master.ui.TestActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, MineModule.class})
public interface MineComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(TestActivity testActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity loginActivity);

    void inject(HomeActivity homeActivity);

    void inject(MineFragment mineFragment);

    void inject(HomeFragment homeFragment);

}
