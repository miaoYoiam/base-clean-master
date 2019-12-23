package com.mine.master.ui.base.view;


import android.support.v4.app.Fragment;

import com.mine.master.internal.HasComponent;
import com.mine.master.internal.components.ApplicationComponent;
import com.mine.master.internal.modules.ActivityModule;

public class BaseFragment extends Fragment {
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent) getActivity()).getComponent());
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return ((BaseActivity) getActivity()).getActivityModule();
    }
}
