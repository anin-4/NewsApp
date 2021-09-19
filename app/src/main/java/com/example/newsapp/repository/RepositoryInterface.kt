package com.example.newsapp.repository

import com.example.newsapp.domain.NewsAppDomain


interface RepositoryInterface {

    suspend fun getListOfNetworkEntityBreaking(page:Int):List<NewsAppDomain>

    suspend fun pushIntoDatabase(newsAppDomain: NewsAppDomain)

    suspend fun deleteFromDatabase(newsAppDomain: NewsAppDomain)

    suspend fun getListFromDatabase():List<NewsAppDomain>

    suspend fun getListOfNetworkEntitySearch(inputQuery:String,page:Int):List<NewsAppDomain>

}