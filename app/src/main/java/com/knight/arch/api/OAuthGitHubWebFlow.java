package com.knight.arch.api;

import com.knight.arch.model.AccessTokenResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import rx.Observable;

/**
 * @author andyiac
 * @date 6/11/23
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 * GitHub Auth Web Application Flow
 */
public interface OAuthGitHubWebFlow {
    //POST https://github.com/login/oauth/access_token

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    Observable<AccessTokenResponse> getOAuthToken(@Field("client_id") String client_id,
                                                  @Field("client_secret") String client_secret, @Field("code") String code);

}
