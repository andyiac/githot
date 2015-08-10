package com.knight.arch.api;

import com.knight.arch.model.Pagination;
import com.knight.arch.model.PersonInfo;

import retrofit.http.GET;
import rx.Observable;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 * <p/>
 * RxJava Style
 */
public interface ApiService {
    @GET("/getdata")
    Observable<Pagination<PersonInfo>> getDataRxJava();
}
