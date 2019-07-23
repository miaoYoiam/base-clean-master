package com.mine.data.net.api;

import com.mine.data.net.HttpResponse;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * api version 2.0
 */
public interface Api {
    @POST("user_server/client/send_code")
    Observable<HttpResponse> getCaptcha(@Body GetCaptchaParams getCaptchaParams);//获取验证码

    @POST("bdapp/home/0/20")
    Observable<HttpResponse> getRecipeHome(@Body GetRecipeHomeParams getRecipeHomeParams);//获取验证码

}
