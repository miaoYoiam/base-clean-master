package com.mine.master.ui.view;

import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.master.ui.base.view.MvpView;

public interface HomeFragmentView extends MvpView {
    void getPersonalResumeListDataSuccess(PersonalResumeListResult personalResumeListResult);

    void getPersonalResumeListDataFail(Throwable e);

    void getCompanyInviteInfoListDataSuccess(CompanyInviteInfoListReuslt companyInviteInfoListReuslt);

    void getCompanyInviteInfoListDataFail(Throwable e);
}
