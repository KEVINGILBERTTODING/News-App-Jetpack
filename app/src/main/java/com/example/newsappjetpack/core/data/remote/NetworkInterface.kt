package com.example.newsappjetpack.core.data.remote

import com.example.newsappjetpack.core.services.network.ApiInterface
import com.example.newsappjetpack.util.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInterface {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.END_POINT)  // Ganti dengan base URL API Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}