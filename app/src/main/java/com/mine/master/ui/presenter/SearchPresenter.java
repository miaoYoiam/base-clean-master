package com.mine.master.ui.presenter;

import com.mine.domain.interactor.user.CompanyInviteInfoListUseCase;
import com.mine.domain.interactor.user.PersonalResumeListUseCase;
import com.mine.domain.repository.params.CompanyInviteInfoListParams;
import com.mine.domain.repository.params.PersonalResumeListParams;
import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.SearchView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class SearchPresenter extends MvpBasePresenter<SearchView> {


    private PersonalResumeListUseCase personalResumeListUseCase;
    private CompanyInviteInfoListUseCase companyInviteInfoListUseCase;

    private SearchView searchView;

    @Inject
    public SearchPresenter(PersonalResumeListUseCase personalResumeListUseCase, CompanyInviteInfoListUseCase companyInviteInfoListUseCase) {
        this.personalResumeListUseCase = personalResumeListUseCase;
        this.companyInviteInfoListUseCase = companyInviteInfoListUseCase;
    }

    @Override
    public void attachView(SearchView searchView) {
        super.attachView(searchView);
        this.searchView = searchView;
    }

    public void getPersonalResumeListData(int offset, int limit, String keyword) {
        PersonalResumeListParams params = new PersonalResumeListParams();
        params.offset = offset + "";
        params.limit = limit + "";
        params.title = keyword;
        personalResumeListUseCase.personalResumeListParams = params;
        personalResumeListUseCase.execute(new PersonalResumeListUseCaseObserver(), params);
    }

    private class PersonalResumeListUseCaseObserver extends DisposableObserver<PersonalResumeListResult> {


        @Override
        public void onNext(PersonalResumeListResult personalResumeListResult) {
            if (searchView != null) {
                searchView.getPersonalResumeListDataSuccess(personalResumeListResult);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (searchView != null) {
                searchView.getPersonalResumeListDataFail(e);
            }
        }

        @Override
        public void onComplete() {

        }
    }

    public void getCompanyInviteInfoListData(int offset, int limit, String keyword) {
        CompanyInviteInfoListParams params = new CompanyInviteInfoListParams();
        params.offset = offset + "";
        params.limit = limit + "";
        params.title = keyword;
        companyInviteInfoListUseCase.companyInviteInfoListParams = params;
        companyInviteInfoListUseCase.execute(new CompanyInviteInfoListUseCaseObserver(), params);
    }

    private class CompanyInviteInfoListUseCaseObserver extends DisposableObserver<CompanyInviteInfoListReuslt> {


        @Override
        public void onNext(CompanyInviteInfoListReuslt companyInviteInfoListReuslt) {
            if (searchView != null) {
                searchView.getCompanyInviteInfoListDataSuccess(companyInviteInfoListReuslt);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (searchView != null) {
                searchView.getCompanyInviteInfoListDataFail(e);
            }
        }

        @Override
        public void onComplete() {

        }
    }
}
