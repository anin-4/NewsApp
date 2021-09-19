package com.example.newsapp.repository

import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.network.response.Article

class NetworkMapperImplementation:NetworkMapper<NewsAppDomain,Article> {
    override fun fromNetworkToDomain(networkEntity: Article): NewsAppDomain {
        return NewsAppDomain(
            id=null,
            author = networkEntity.author,
            content = networkEntity.content,
            description = networkEntity.description,
            publishedAt = networkEntity.publishedAt,
            source = networkEntity.source,
            title = networkEntity.title,
            url=networkEntity.url,
            urlToImage = networkEntity.urlToImage
        )
    }

    fun fromNetworkListToDomainList(listNetworkEntity:List<Article>):List<NewsAppDomain>{
        return listNetworkEntity.map{
            fromNetworkToDomain(it)
        }
    }
}