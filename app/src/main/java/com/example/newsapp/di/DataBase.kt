package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.repository.EntityMapperImplementation
import com.example.newsapp.room.NewsAppDao
import com.example.newsapp.room.NewsAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBase {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context):NewsAppDatabase{
            return Room.databaseBuilder(
                context,
                NewsAppDatabase::class.java,
                "room_entity"
            ).build()
    }

    @Singleton
    @Provides
    fun provideDao(newsAppDatabase: NewsAppDatabase):NewsAppDao=newsAppDatabase.NewsAppDao()



    @Provides
    @Singleton
    fun provideEntityMapperImplementation(): EntityMapperImplementation {
        return EntityMapperImplementation()
    }
}