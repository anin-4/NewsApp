package com.example.newsapp.domain

import com.example.newsapp.network.response.Source

data class NewsAppDomain(
    val id:Int?=null,
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String?=null,
    val source: Source?=null,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null

)