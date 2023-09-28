package com.amttech.imageapp.retrofit

import com.amttech.imageapp.data.entity.ImagesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesInterface {
    @GET("api/?key=30381239-ba2e4952567c393c6c5294c25")
    fun searchResult(@Query("q") term: String): Call<ImagesResponse>

    @GET("api/?key=30381239-ba2e4952567c393c6c5294c25")
    fun searchResultById(@Query("id") imageId: String): Call<ImagesResponse>
}