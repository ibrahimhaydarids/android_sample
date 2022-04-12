package com.example.mytesting.data.network

import com.example.mytesting.model.ResponseGallery
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("GetGallery")
    suspend fun getGallery(@Query("categoryId")  page:Int): ResponseGallery

}