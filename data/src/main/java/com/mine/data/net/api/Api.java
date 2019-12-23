package com.mine.data.net.api;

import com.mine.data.net.HttpResponse;
import com.mine.domain.repository.params.CompanyInviteInfoListParams;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;
import com.mine.domain.repository.params.PersonalResumeListParams;
import com.mine.domain.repository.params.UserLoginParams;
import com.mine.domain.repository.params.UserRegisterParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Api {
    @POST("user_server/client/send_code")
    Observable<HttpResponse> getCaptcha(@Body GetCaptchaParams getCaptchaParams);//获取验证码

    @POST("bdapp/home/{offset}/{num}")
    Observable<HttpResponse> getRecipeHome(
            @Body GetRecipeHomeParams getRecipeHomeParams
            , @Path("offset") String offset
            , @Path("num") String num);

    @POST("user/loginByPhone.do")
    Observable<HttpResponse> userLogin(@Body UserLoginParams userLoginParams);//用户登录

    @POST("user/register.do")
    Observable<HttpResponse>  userRegister(@Body UserRegisterParams userLoginParams);//用户注册

    @POST("personal/resume.do")
    Observable<HttpResponse>  getPersonalResumeData(@Body PersonalResumeListParams personalResumeListParams);//用户注册

    @POST("company/resume.do")
    Observable<HttpResponse>  getCompanyInfoData(@Body CompanyInviteInfoListParams companyInviteInfoListParams);//用户注册


}
