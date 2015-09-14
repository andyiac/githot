package com.knight.arch.api;

import com.knight.arch.data.Pagination;
import com.knight.arch.data.Users;
import com.knight.arch.model.PersonInfo;
import com.knight.arch.model.User;

import retrofit.http.GET;
import retrofit.http.Query;
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

    @GET("/search/users")
    Observable<Users<User>> getUsersRxJava(@Query(value = "q",encodeValue = false)  String query, @Query("page") int pageId);

//     void list(@Query(value="foo", encodeValue=false) String foo);
}
