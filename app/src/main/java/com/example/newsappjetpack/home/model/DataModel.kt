package com.example.newsappjetpack.home.model

import com.google.gson.annotations.SerializedName

data class DataModel (
    @SerializedName("link") val link : String,
    @SerializedName("image") val image : String,
    @SerializedName("description") val description : String,
    @SerializedName("title") val title : String,
    @SerializedName("posts") val posts : List<PostModel>,
)

