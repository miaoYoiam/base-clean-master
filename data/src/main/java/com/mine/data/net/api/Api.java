package com.mine.data.net.api;

import com.mine.data.net.HttpResponse;
import com.mine.domain.repository.params.CompanyInviteInfoListParams;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;
import com.mine.domain.repository.params.PersonalResumeListParams;
import com.mine.domain.repository.params.SearchListParams;
import com.mine.domain.repository.params.UploadCompanyInviteParams;
import com.mine.domain.repository.params.UploadPersonalResumeParams;
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
    Observable<HttpResponse> userRegister(@Body UserRegisterParams userLoginParams);//用户注册

    @POST("personal/resume.do")
    Observable<HttpResponse> getPersonalResumeData(@Body PersonalResumeListParams personalResumeListParams);//首页简历集合

    @POST("company/resume.do")
    Observable<HttpResponse> getCompanyInfoData(@Body CompanyInviteInfoListParams companyInviteInfoListParams);//首页招聘集合

    @POST("personal/upload.do")
    Observable<HttpResponse> uploadPersonalResume(@Body UploadPersonalResumeParams uploadPersonalResumeParams);//上传个人简历

    @POST("company/upload.do")
    Observable<HttpResponse> uploadCompanyInvite(@Body UploadCompanyInviteParams uploadCompanyInviteParams);//上传公司招聘

    @POST("company/resume.do")
    Observable<HttpResponse> searchListData(@Body SearchListParams searchListParams);//搜索结果


}
