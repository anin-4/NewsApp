package com.example.newsapp.room

import androidx.room.*


@Dao
interface NewsAppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newsAppRoomEntity: NewsAppRoomEntity)

    @Delete
    suspend fun delete(newsAppRoomEntity: NewsAppRoomEntity)

    @Query("select * from room_entity")
    suspend fun getAllFavs():List<NewsAppRoomEntity>
}