package com.example.myapplication;

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface BokjiService{
        @GET("/find")
        fun getlist(
            @Query("catemid") cate_mid: String,
            @Query("catelow") cate_low: String
        ): Call<Response?>

}