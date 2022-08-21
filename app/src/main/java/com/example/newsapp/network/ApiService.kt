package com.example.newsapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getNews(
        @Query("country") id: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}