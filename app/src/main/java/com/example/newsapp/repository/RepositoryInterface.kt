package com.example.newsapp.repository


import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.network.response.Article
import com.example.newsapp.network.response.NetworkResponse
import retrofit2.Response


interface RepositoryInterface {

    suspend fun pushIntoDatabase(item: Article)

    suspend fun deleteFromDatabase(newsAppDomain: NewsAppDomain)

    suspend fun getListFromDatabase():List<NewsAppDomain>

    suspend fun getBreakingNews(pageNumber:Int,country:String):Response<NetworkResponse>

    suspend fun getSearchNews(query:String, pageNumber: Int):Response<NetworkResponse>




}