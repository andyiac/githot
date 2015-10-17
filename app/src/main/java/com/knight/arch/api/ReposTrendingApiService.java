package com.knight.arch.api;

import com.knight.arch.data.Repositories;
import com.knight.arch.data.Users;
import com.knight.arch.model.Repository;
import com.knight.arch.model.User;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author andyiac
 * @date 15-10-17
 * @web http://blog.andyiac.com/
 * <p/>
 */
public interface ReposTrendingApiService {

    //http://trending.codehub-app.com/v2/trending?language=c&since=weekly
    @GET("/v2/trending")
    Observable<List<Repository>> getTrendingRepositories(@Query(value = "language") String language, @Query(value = "since") String since);
}
