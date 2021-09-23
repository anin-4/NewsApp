package com.example.newsapp.network.response

data class NetworkResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)