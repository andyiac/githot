package com.knight.arch.api;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.knight.arch.data.AllPersonlInfos;
import com.knight.arch.data.Pagination;
import com.knight.arch.model.PersonInfo;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by summer on 15-7-30.
 *
 * @web http://blog.andyiac.com/
 */
public class ApiClient {
    static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s
    static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20s
    private static final String BASE_URL = "http://mock-api.com/TyTabSFqXNyqMpNw.mock";
    private static TestDemoApiInterface testDemoApiInterface;

    /**
     * 用于Stethoscope调试的ttpClient
     */
    public static OkClient getOkClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.networkInterceptors().add(new StethoInterceptor());
        return new OkClient(client);
    }

    public static TestDemoApiInterface getTestDemoApiClient() {
        if (testDemoApiInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setClient(getOkClient())
                    .setEndpoint(BASE_URL)
                    .build();

            testDemoApiInterface = restAdapter.create(TestDemoApiInterface.class);
        }

        return testDemoApiInterface;
    }

    public interface TestDemoApiInterface {
        @GET("/getdata")
        void getStreams(@Query("limit") int limit, @Query("offset") int offset, Callback<List<AllPersonlInfos>> callback);

        @GET("/getdata")
        void getData2(Callback<AllPersonlInfos> callback);

        @GET("/getdata")
        Observable<Pagination<PersonInfo>> getDataRxJava();
    }
}
