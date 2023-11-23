package com.example.android_project;
import com.example.android_project.modle.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenDataSoftApi {

    @GET("api/explore/v2.1/catalog/datasets/prix-des-carburants-j-1/records")
    Call<ApiResponse> getStations(
            @Query("where") String whereCondition,
            @Query("fields") String fields,
            @Query("order_by") String orderBy,
            @Query("limit") int limit,
            @Query("offset") int offset
    );
}
