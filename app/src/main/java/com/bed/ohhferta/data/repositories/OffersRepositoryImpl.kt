package com.bed.ohhferta.data.repositories

import com.bed.ohhferta.data.datasources.OffersDatasource

import com.bed.ohhferta.domain.alias.DomainOfferType
import com.bed.ohhferta.domain.repositories.OffersRepository

import com.bed.ohhferta.framework.clients.responses.toEntity

class OffersRepositoryImpl(private val datasource: OffersDatasource) : OffersRepository {
    override suspend fun getAll(vararg parameters: Pair<String, Any>?): DomainOfferType {
        val response = datasource.getAll(*parameters)

        return response
            .mapLeft { failure -> failure.toEntity() }
            .map { success -> success.items.map { it.toEntity() } }
    }
}
