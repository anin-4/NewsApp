package com.example.newsapp.repository

import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.network.response.Article
import com.example.newsapp.network.response.Source
import com.example.newsapp.room.NewsAppRoomEntity

class EntityMapperImplementation:EntityMapper<NewsAppDomain,NewsAppRoomEntity>{

    override fun fromDomainToEntity(domainEntity: NewsAppDomain): NewsAppRoomEntity {
        return NewsAppRoomEntity(
            author= domainEntity.author,
            description = domainEntity.description,
            content = domainEntity.content,
            publishedAt = domainEntity.publishedAt,
            source = domainEntity.source,
            title = domainEntity.title,
            url= domainEntity.url,
            urlToImage = domainEntity.urlToImage
        )
    }

    override fun fromEntityToDomain(roomEntity: NewsAppRoomEntity): NewsAppDomain {
        return NewsAppDomain(
            id=roomEntity.id,
            author = roomEntity.author,
            content = roomEntity.content,
            description = roomEntity.description,
            publishedAt = roomEntity.publishedAt,
            source = roomEntity.source,
            title = roomEntity.title,
            url= roomEntity.url,
            urlToImage = roomEntity.urlToImage
        )
    }


    fun fromEntityToDomainList(roomEntityList: List<NewsAppRoomEntity>):List<NewsAppDomain>{
        return roomEntityList.map{
            fromEntityToDomain(it)
        }
    }

    override fun fromArticleToEntity(item: Article): NewsAppRoomEntity {
        return NewsAppRoomEntity(
            author= item.author,
            description = item.description,
            content = item.content,
            publishedAt = item.publishedAt,
            source = item.source,
            title = item.title,
            url= item.url,
            urlToImage = item.urlToImage
        )
    }


}