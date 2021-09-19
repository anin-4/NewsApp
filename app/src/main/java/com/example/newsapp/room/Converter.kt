package com.example.newsapp.room

import androidx.room.TypeConverter
import com.example.newsapp.network.response.Source


class Converter {

    @TypeConverter
    fun fromSourceToString(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun fromStringToSource(name:String):Source{
        return Source(name,name)
    }
}