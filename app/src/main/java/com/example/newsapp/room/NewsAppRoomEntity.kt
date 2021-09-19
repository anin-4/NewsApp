package com.example.newsapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.network.response.Source


@Entity(tableName = "room_entity")
data class NewsAppRoomEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,

    @ColumnInfo
    val author:String?=null,

    @ColumnInfo
    val description:String?=null,


    @ColumnInfo
    val content:String?=null,


    @ColumnInfo
    val publishedAt: String?=null,

    @ColumnInfo
    val source: Source?=null,

    @ColumnInfo
    val title: String?=null,

    @ColumnInfo
    val url: String?=null,

    @ColumnInfo
    val urlToImage: String?=null

)
