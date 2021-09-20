package com.example.newsapp.di

import com.example.newsapp.network.RetrofitService
import com.example.newsapp.repository.EntityMapperImplementation
import com.example.newsapp.repository.RepositoryImplementation
import com.example.newsapp.repository.RepositoryInterface
import com.example.newsapp.room.NewsAppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Repodependency {

    @Singleton
    @Provides
    fun provideRepo(
        newsAppDao: NewsAppDao,
        entityMapperImplementation: EntityMapperImplementation,
        retrofitService: RetrofitService
    ):RepositoryInterface{
        return RepositoryImplementation(newsAppDao,entityMapperImplementation,retrofitService)
    }
}