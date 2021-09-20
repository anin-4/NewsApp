package com.example.newsapp.network

import com.example.newsapp.utilspackage.Constants.NEWS_API_KEY
import com.example.newsapp.network.response.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country:String="us",
        @Query("page")
        page:Int=1,
        @Query("apiKey")
        apiKey:String=NEWS_API_KEY
    ):NetworkResponse

    @GET("everything")
    suspend fun getSearchResults(
        @Query("q")
        q:String="sports",
        @Query("page")
        page:Int=1,
        @Query("apiKey")
        apiKey:String=NEWS_API_KEY
    ):NetworkResponse

}