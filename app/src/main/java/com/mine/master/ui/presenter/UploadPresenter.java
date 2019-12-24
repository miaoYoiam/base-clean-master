package com.mine.master.ui.presenter;

import com.mine.domain.interactor.user.UploadCompanyInviteUseCase;
import com.mine.domain.interactor.user.UploadPersonalResumeUseCase;
import com.mine.domain.repository.params.UploadCompanyInviteParams;
import com.mine.domain.repository.params.UploadPersonalResumeParams;
import com.mine.domain.result.UploadCompanyInviteResult;
import com.mine.domain.result.UploadPersonalResumeResult;
import com.mine.master.ui.base.presenter.MvpBasePresenter;
import com.mine.master.ui.view.UploadView;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class UploadPresenter extends MvpBasePresenter<UploadView> {

    private UploadPersonalResumeUseCase uploadPersonalResumeUseCase;
    private UploadCompanyInviteUseCase uploadCompanyInviteUseCase;

    private UploadView uploadView;

    @Inject
    public UploadPresenter(UploadPersonalResumeUseCase uploadPersonalResumeUseCase, UploadCompanyInviteUseCase uploadCompanyInviteUseCase) {
        this.uploadPersonalResumeUseCase = uploadPersonalResumeUseCase;
        this.uploadCompanyInviteUseCase = uploadCompanyInviteUseCase;
    }

    @Override
    public void attachView(UploadView uploadView) {
        super.attachView(uploadView);
        this.uploadView = uploadView;
    }

    public void uploadPersonalResume(String title, String authorName, String age, String sex, String phone, String expectSalary
            , String location, String workYear, String workExperience, String selfEvaluation, String professionalSkill) {
        UploadPersonalResumeParams params = new UploadPersonalResumeParams();
        params.id = System.currentTimeMillis() + "";
        params.title = title;
        params.author = authorName;
        params.age = Integer.parseInt(age);
        params.sex = sex;
        params.phone = phone;
        params.expectSalary = expectSalary;
        params.location = location;
        params.workYears = workYear;
        params.workExperience = workExperience;
        params.selfEvaluation = selfEvaluation;
        params.professionalSkill = professionalSkill;
        params.createDate = new Date().toString();
        uploadPersonalResumeUseCase.execute(new UploadPersonalResumeUseCaseObserver(), params);
    }

    private class UploadPersonalResumeUseCaseObserver extends DisposableObserver<UploadPersonalResumeResult> {

        @Override
        public void onNext(UploadPersonalResumeResult uploadPersonalResumeResult) {
            if (uploadView != null) {
                uploadView.uploadPersonalResumeSuccess(uploadPersonalResumeResult);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (uploadView != null) {
                uploadView.uploadPersonalResumeFail(e.getMessage());
            }
        }

        @Override
        public void onComplete() {

        }
    }


    private class UploadCompanyInviteUseCaseObserver extends DisposableObserver<UploadCompanyInviteResult> {

        @Override
        public void onNext(UploadCompanyInviteResult uploadCompanyInviteResult) {
            if (uploadView != null) {
                uploadView.uploadCompanyInviteSuccess(uploadCompanyInviteResult);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (uploadView != null) {
                uploadView.uploadCompanyInviteFail(e.getMessage());
            }
        }

        @Override
        public void onComplete() {

        }
    }

    public void uploadCompanyInvite(String title, String location, String name, String expectSalary, String phone, String workYears, String workExperience, String workDescribe, String companyDescribeStr) {
        UploadCompanyInviteParams params = new UploadCompanyInviteParams();
        params.title = title;
        params.location = location;
        params.companyName = name;
        params.expectSalary = expectSalary;
        params.phone = phone;
        params.workYears = workYears;
        params.workExperience = workExperience;
        params.jobDescribe = workDescribe;
        params.companyDescribe = companyDescribeStr;
        params.createDate = new Date().toString();
        uploadCompanyInviteUseCase.execute(new UploadCompanyInviteUseCaseObserver(), params);
    }
}
