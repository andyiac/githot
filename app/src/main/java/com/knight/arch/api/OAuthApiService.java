package com.knight.arch.api;

import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author andyiac
 * @date 15/10/23
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public interface OAuthApiService {

    @Headers({
            "Content-Length: 0"
    })
    void getUserDataWithToken();

    @Headers({
            "Content-Length: 0"
    })
    @PUT("/user/starred/{user}/{repo}")
    Observable<Object> starRepos(@Path("user") String user, @Path("repo") String repo,
                                 @Query(value = "access_token", encodeValue = true) String accessToken);

}
