package com.mine.master.ui.base.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mine.master.App;
import com.mine.master.R;
import com.mine.master.internal.components.ApplicationComponent;
import com.mine.master.internal.modules.ActivityModule;
import com.mine.master.utils.Logger;
import com.mine.master.utils.StatusBarHelper;

public class BaseActivity extends AppCompatActivity {
    private ActivityModule mActivityModule;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initStatusBarColor();
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setActionBarStyle();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setActionBarStyle();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setActionBarStyle();
    }
    private void setActionBarStyle() {
        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        } catch (Exception e) {
            Logger.w(e + "Toolbar Missing");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // clear FLAG_TRANSLUCENT_STATUS flag:
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            StatusBarHelper.setStatusBarLightMode(this);
        }

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
