package com.mine.master.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mine.master.App;
import com.mine.master.internal.components.ApplicationComponent;
import com.mine.master.internal.modules.ActivityModule;

public class BaseActivity extends AppCompatActivity {
    private ActivityModule mActivityModule;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    protected ActivityModule getActivityModule(){
        if (this. mActivityModule== null) {
            this.mActivityModule = new ActivityModule(this);
        }
        return this.mActivityModule;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }
}
