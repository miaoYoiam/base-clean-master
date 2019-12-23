package com.mine.master.ui.presenter;

import com.mine.domain.interactor.user.CompanyInviteInfoListUseCase;
import com.mine.domain.interactor.user.PersonalResumeListUseCase;
import com.mine.domain.repository.params.CompanyInviteInfoListParams;
import com.mine.domain.repository.params.PersonalResumeListParams;
import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.HomeFragmentView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class HomeFragmentPresenter extends MvpBasePresenter<HomeFragmentView> {

    private PersonalResumeListUseCase personalResumeListUseCase;
    private CompanyInviteInfoListUseCase companyInviteInfoListUseCase;

    private HomeFragmentView homeFragmentView;

    @Inject
    public HomeFragmentPresenter(PersonalResumeListUseCase personalResumeListUseCase, CompanyInviteInfoListUseCase companyInviteInfoListUseCase) {
        this.personalResumeListUseCase = personalResumeListUseCase;
        this.companyInviteInfoListUseCase = companyInviteInfoListUseCase;
    }

    @Override
    public void attachView(HomeFragmentView homeFragmentView) {
        super.attachView(homeFragmentView);
        this.homeFragmentView = homeFragmentView;
    }

    public void getPersonalResumeListData(int offset, int limit) {
        PersonalResumeListParams params = new PersonalResumeListParams();
        params.offset = offset + "";
        params.limit = limit + "";
        personalResumeListUseCase.personalResumeListParams = params;
        personalResumeListUseCase.execute(new PersonalResumeListUseCaseObserver(), params);
    }

    private class PersonalResumeListUseCaseObserver extends DisposableObserver<PersonalResumeListResult> {


        @Override
        public void onNext(PersonalResumeListResult personalResumeListResult) {
            if (homeFragmentView != null) {
                homeFragmentView.getPersonalResumeListDataSuccess(personalResumeListResult);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (homeFragmentView != null) {
                homeFragmentView.getPersonalResumeListDataFail(e);
            }
        }

        @Override
        public void onComplete() {

        }
    }

    public void getCompanyInviteInfoListData(int offset, int limit) {
        CompanyInviteInfoListParams params = new CompanyInviteInfoListParams();
        params.offset = offset + "";
        params.limit = limit + "";
        companyInviteInfoListUseCase.companyInviteInfoListParams = params;
        companyInviteInfoListUseCase.execute(new CompanyInviteInfoListUseCaseObserver(), params);
    }

    private class CompanyInviteInfoListUseCaseObserver extends DisposableObserver<CompanyInviteInfoListReuslt> {


        @Override
        public void onNext(CompanyInviteInfoListReuslt companyInviteInfoListReuslt) {
            if (homeFragmentView != null) {
                homeFragmentView.getCompanyInviteInfoListDataSuccess(companyInviteInfoListReuslt);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (homeFragmentView != null) {
                homeFragmentView.getCompanyInviteInfoListDataFail(e);
            }
        }

        @Override
        public void onComplete() {

        }
    }
}
