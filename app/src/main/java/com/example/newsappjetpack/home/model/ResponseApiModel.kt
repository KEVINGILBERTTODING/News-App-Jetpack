package com.example.newsappjetpack.home.model

import android.provider.ContactsContract.Data
import com.google.gson.annotations.SerializedName

data class ResponseApiModel(

    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: DataModel,

)