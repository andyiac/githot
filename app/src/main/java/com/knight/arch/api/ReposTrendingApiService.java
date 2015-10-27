package com.knight.arch.api;

import com.knight.arch.model.Repository;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author andyiac
 * @date 15-10-17
 * @web http://blog.andyiac.com/
 * <p/>
 */
public interface ReposTrendingApiService {

    @GET("/v2/trending")
    Observable<List<Repository>> getTrendingRepositories(@Query(value = "language") String language, @Query(value = "since") String since);
}
