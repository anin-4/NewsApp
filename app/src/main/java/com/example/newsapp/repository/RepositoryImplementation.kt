package com.example.newsapp.repository

import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.network.RetrofitService
import com.example.newsapp.room.NewsAppDao
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val newsAppDao: NewsAppDao,
    private val entityMapperImplementation: EntityMapperImplementation,
    private val retrofitService: RetrofitService,
    private val networkMapperImplementation: NetworkMapperImplementation
):RepositoryInterface {
    override suspend fun getListOfNetworkEntityBreaking(pager:Int): List<NewsAppDomain> {
        return networkMapperImplementation.fromNetworkListToDomainList(retrofitService.getBreakingNews(page=pager).articles)
    }

    override suspend fun pushIntoDatabase(newsAppDomain: NewsAppDomain) {
        newsAppDao.insert(entityMapperImplementation.fromDomainToEntity(newsAppDomain))
    }

    override suspend fun deleteFromDatabase(newsAppDomain: NewsAppDomain) {
        newsAppDao.delete(entityMapperImplementation.fromDomainToEntity(newsAppDomain))
    }

    override suspend fun getListFromDatabase(): List<NewsAppDomain> {
        return entityMapperImplementation.fromEntityToDomainList(newsAppDao.getAllFavs())
    }

    override suspend fun getListOfNetworkEntitySearch(inputQuery:String, page:Int): List<NewsAppDomain> {
        return networkMapperImplementation.fromNetworkListToDomainList(retrofitService.getSearchResults(q=inputQuery,page=page).articles)
    }
}