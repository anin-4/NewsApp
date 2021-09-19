package com.example.newsapp.repository

interface NetworkMapper<DomainEntity,NetworkEntity> {

    fun fromNetworkToDomain(networkEntity: NetworkEntity):DomainEntity


}