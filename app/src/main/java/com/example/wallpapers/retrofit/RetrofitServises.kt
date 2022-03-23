package com.example.retrofit.retrofit

import com.example.wallpapers.models.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServises {
    @GET("photos/random")
    fun getPhotosList(@Query("client_id") s:String,@Query("query")t:String,@Query("count")n:Int=30): Call<List<Photo>>


}