package com.mine.data.net.api;

import com.mine.data.net.HttpResponse;
import com.mine.domain.repository.params.GetCaptchaParams;
import com.mine.domain.repository.params.GetRecipeHomeParams;

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

}
