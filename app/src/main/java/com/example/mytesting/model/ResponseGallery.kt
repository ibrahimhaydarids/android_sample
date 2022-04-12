package com.example.mytesting.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseGallery(
    @SerializedName("Success")
    @Expose
    val success: String?="",

    @SerializedName("Message")
    @Expose
    val message: String?="",

    @SerializedName("Items")
    @Expose
    val items : ArrayList<Item>?
)