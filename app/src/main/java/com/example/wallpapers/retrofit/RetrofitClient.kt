package com.example.retrofit.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit : Retrofit? = null
    fun getClient(baseUri : String) : Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(baseUri)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }
}