package com.mine.master.ui.view;

import com.mine.domain.result.UploadCompanyInviteResult;
import com.mine.domain.result.UploadPersonalResumeResult;
import com.mine.master.ui.base.view.MvpView;

public interface UploadView extends MvpView {
    void uploadPersonalResumeSuccess(UploadPersonalResumeResult uploadPersonalResumeResult);

    void uploadPersonalResumeFail(String message);


    void uploadCompanyInviteFail(String message);

    void uploadCompanyInviteSuccess(UploadCompanyInviteResult uploadCompanyInviteResult);
}
