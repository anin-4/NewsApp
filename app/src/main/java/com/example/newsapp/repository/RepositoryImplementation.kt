package com.example.newsapp.repository

import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.network.RetrofitService
import com.example.newsapp.network.response.NetworkResponse
import com.example.newsapp.room.NewsAppDao
import retrofit2.Response
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val newsAppDao: NewsAppDao,
    private val entityMapperImplementation: EntityMapperImplementation,
    private val retrofitService: RetrofitService
):RepositoryInterface {

    override suspend fun pushIntoDatabase(newsAppDomain: NewsAppDomain) {
        newsAppDao.insert(entityMapperImplementation.fromDomainToEntity(newsAppDomain))
    }

    override suspend fun deleteFromDatabase(newsAppDomain: NewsAppDomain) {
        newsAppDao.delete(entityMapperImplementation.fromDomainToEntity(newsAppDomain))
    }

    override suspend fun getListFromDatabase(): List<NewsAppDomain> {
        return entityMapperImplementation.fromEntityToDomainList(newsAppDao.getAllFavs())
    }

    override suspend fun getBreakingNews(
        pageNumber: Int,
        country: String
    ): Response<NetworkResponse> {
        return retrofitService.getBreakingNews(country,pageNumber)
    }

    override suspend fun getSearchNews(query: String, pageNumber: Int): Response<NetworkResponse> {
        return retrofitService.getSearchResults(query,pageNumber)
    }


}