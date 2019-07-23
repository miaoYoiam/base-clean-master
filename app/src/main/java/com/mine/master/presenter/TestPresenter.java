package com.mine.master.presenter;

import android.util.Log;

import com.mine.domain.interactor.user.GetCaptchaUseCase;
import com.mine.domain.interactor.user.GetRecipeHomeUseCase;
import com.mine.domain.model.GetCaptheResult;
import com.mine.domain.model.GetRecipeHomeResult;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;
import com.mine.master.base.presenter.MvpBasePresenter;
import com.mine.master.view.TestView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TestPresenter extends MvpBasePresenter<TestView> {
    private static final String TAG = TestPresenter.class.getSimpleName();

    private GetCaptchaUseCase getCaptchaUseCase;

    private GetRecipeHomeUseCase getReipeHomeUseCase;

    @Inject
    public TestPresenter(GetCaptchaUseCase getCaptchaUseCase,
                         GetRecipeHomeUseCase getReipeHomeUseCase) {
        this.getCaptchaUseCase = getCaptchaUseCase;
        this.getReipeHomeUseCase = getReipeHomeUseCase;
    }

    private class GetCaptchaUserCaseObserver extends DisposableObserver<GetCaptheResult> {

        @Override
        public void onNext(GetCaptheResult getCaptheResult) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    }


    public void getGaptcha() {
        GetCaptchaParams getCaptchaParams = new GetCaptchaParams();
        getCaptchaParams.setPhone("");
        getCaptchaUseCase.setParams(getCaptchaParams);
        this.getCaptchaUseCase.execute(new GetCaptchaUserCaseObserver(), getCaptchaParams);
    }


    private class GetRecipeHomeUserCaseObserver extends DisposableObserver<GetRecipeHomeResult> {

        @Override
        public void onNext(GetRecipeHomeResult getRecipeHomeResult) {
            Log.e(TAG, "onNext: "+getRecipeHomeResult );
        }

        @Override
        public void onError(Throwable t) {
            Log.e(TAG, "=====>" + t.getMessage());
        }

        @Override
        public void onComplete() {
            Log.e(TAG, "=====>");
        }
    }

    public void getRecipeHome(int requestCount, int offset, int num) {
        GetRecipeHomeParams getRecipeHomeParams = new GetRecipeHomeParams();
        getRecipeHomeParams.requestCount = requestCount + "";
        getReipeHomeUseCase.execute(new GetRecipeHomeUserCaseObserver(), getRecipeHomeParams,offset+"",num+"");

    }

}
