package com.example.retrofit.retrofit

object Common {

    private val BASE_URL = "https://api.unsplash.com/"
    val retrofitServises : RetrofitServises
        get() =RetrofitClient.getClient(BASE_URL).create(RetrofitServises::class.java)

}