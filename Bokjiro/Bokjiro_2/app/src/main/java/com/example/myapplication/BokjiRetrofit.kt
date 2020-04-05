package com.example.myapplication;

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BokjiRetrofit(cate_mid: String, cate_low: String) {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://52.79.72.52:5000")
        .client(requestHeader)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(BokjiService::class.java)

    var response = service.getlist(cate_mid, cate_low)

    private val requestHeader: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(interceptor).build()
        }
}