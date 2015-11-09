package com.knight.arch.api;

import com.knight.arch.data.Repositories;
import com.knight.arch.data.Users;
import com.knight.arch.model.Repository;
import com.knight.arch.model.User;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 * <p>
 * RxJava Style
 */
public interface ApiService {

    @GET("/search/users")
    Observable<Users<User>> getUsersRxJava(@Query(value = "q", encodeValue = false) String query, @Query("page") int pageId);

    @GET("/search/repositories")
    Observable<Repositories<Repository>> getRepositories(@Query(value = "q", encodeValue = false) String query, @Query("page") int pageId);

    @GET("/users/{user}/repos")
    Observable<List<Repository>> getUserRepositories(@Path("user") String user);

    @Headers({
            "Content-Length: 0"
    })
    @PUT("/user/starred/{user}/{repo}")
    Observable<Object> starRepos(@Path("user") String user, @Path("repo") String repo,
                                 @Query(value = "access_token", encodeValue = true) String accessToken);

    @Headers({
            "Content-Length: 0"
    })
    void getUserDataWithToken();

}
