/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mine.domain.repository;

import com.mine.domain.repository.params.CompanyInviteInfoListParams;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;
import com.mine.domain.repository.params.PersonalResumeListParams;
import com.mine.domain.repository.params.UserLoginParams;
import com.mine.domain.repository.params.UserRegisterParams;
import com.mine.domain.result.CompanyInviteInfoListReuslt;
import com.mine.domain.result.GetCaptheResult;
import com.mine.domain.result.GetRecipeHomeResult;
import com.mine.domain.result.PersonalResumeListResult;
import com.mine.domain.result.UserLoginResult;
import com.mine.domain.result.UserRegisterResult;

import io.reactivex.Observable;


public interface UserRepository {
    Observable<GetCaptheResult> getCaptcha(GetCaptchaParams getCaptchaParams);

    Observable<GetRecipeHomeResult> getRecipeHome(GetRecipeHomeParams getRecipeHomeParams, String offset, String num);

    Observable<UserLoginResult> userLogin(UserLoginParams userLoginParams);

    Observable<UserRegisterResult> userRegister(UserRegisterParams userLoginParams);

    Observable<PersonalResumeListResult> getPersonalResumeListData(PersonalResumeListParams personalResumeListParams);

    Observable<CompanyInviteInfoListReuslt> getCompanyInviteInfoListData(CompanyInviteInfoListParams companyInviteInfoListParams);

}
