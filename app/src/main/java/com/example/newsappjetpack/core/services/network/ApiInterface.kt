package com.example.newsappjetpack.core.services.network

import com.example.newsappjetpack.home.model.ResponseApiModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("antara/terbaru")
    suspend fun GetNews(): ResponseApiModel
}