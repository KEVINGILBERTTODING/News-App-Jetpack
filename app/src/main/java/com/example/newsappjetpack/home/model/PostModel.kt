package com.example.newsappjetpack.home.model

import com.google.gson.annotations.SerializedName

data class PostModel(


    @SerializedName("link") val link: String,
    @SerializedName("title") val title: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbail: String,
)