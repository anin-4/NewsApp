package com.example.newsapp.repository



interface EntityMapper<DomainEntity,RoomEntity> {

    fun fromDomainToEntity(domainEntity: DomainEntity):RoomEntity

    fun fromEntityToDomain(roomEntity: RoomEntity):DomainEntity
}