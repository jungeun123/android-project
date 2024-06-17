package com.cookandroid.android_project1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrugApiService {
    @GET("getDrbEasyDrugList")
    Call<ApiResponse> getDrugList(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") int pageNo,
            @Query("numOfRows") int numOfRows,
            @Query("efcyQesitm") String efcyQesitm,
            @Query("type") String type
    );
}
