package com.knight.arch.api;

import com.knight.arch.data.Repositories;
import com.knight.arch.data.Users;
import com.knight.arch.model.Repository;
import com.knight.arch.model.User;

import java.util.List;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 * <p>
 * RxJava Style
 */
public interface ApiService {

    @GET("/search/users")
    Observable<Users<User>> getUsersRxJava(
            @Query(value = "q", encodeValue = false) String query,
            @Query("page") int pageId
    );

    @GET("/search/users")
    Observable<Users<User>> getUsersRxJava(
            @Query(value = "q", encodeValue = false) String query,
            @Query("page") int pageId,
            @Query(value = "access_token", encodeValue = true) String accessToken
    );

    @GET("/search/repositories")
    Observable<Repositories<Repository>> getRepositories(
            @Query(value = "q", encodeValue = false) String query,
            @Query("page") int pageId
    );

    @GET("/search/repositories")
    Observable<Repositories<Repository>> getRepositories(
            @Query(value = "q", encodeValue = false) String query,
            @Query("page") int pageId,
            @Query(value = "access_token", encodeValue = true) String accessToken
    );


    @GET("/users/{user}/repos")
    Observable<List<Repository>> getUserRepositories(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<List<Repository>> getUserRepositories(
            @Path("user") String user,
            @Query(value = "access_token", encodeValue = true) String accessToken
    );

    //Star a repository
    @Headers({
            "Content-Length: 0",
            "Accept: application/json"
    })
    @PUT("/user/starred/{user}/{repo}")
    Observable<Object> starRepo(
            @Path("user") String user, @Path("repo") String repo,
            @Query(value = "access_token", encodeValue = true) String accessToken
    );

    //Check if you are starring a repository
    @GET("/user/starred/{owner}/{repo}")
    Observable<Response> checkIfReposStarred(
            @Path("owner") String owner, @Path("repo") String repo,
            @Query(value = "access_token", encodeValue = true) String accessToken
    );

    //List repositories being starred
    @GET("/users/{username}/starred")
    void starredRepos(@Path("username") String username);

    //Get user Info
    @GET("/user")
    Observable<User> getUserInfoWithToken(
            @Query(value = "access_token", encodeValue = true) String accessToken
    );

}
