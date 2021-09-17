package com.example.newsapp.network.response

data class NetworkResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)