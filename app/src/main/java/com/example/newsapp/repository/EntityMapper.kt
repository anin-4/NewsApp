package com.example.newsapp.repository


import com.example.newsapp.network.response.Article


interface EntityMapper<DomainEntity,RoomEntity> {

    fun fromDomainToEntity(domainEntity: DomainEntity):RoomEntity

    fun fromEntityToDomain(roomEntity: RoomEntity):DomainEntity

    fun fromArticleToEntity(item: Article):RoomEntity
}