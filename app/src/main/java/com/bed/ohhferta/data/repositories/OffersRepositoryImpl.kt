package com.bed.ohhferta.data.repositories

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.flowOn

import com.bed.ohhferta.data.datasources.OffersDatasource

import com.bed.ohhferta.framework.clients.responses.toEntity

import com.bed.ohhferta.domain.result.Result
import com.bed.ohhferta.domain.alias.DomainOfferType

import com.bed.ohhferta.domain.repositories.offers.OffersRepository
import com.bed.ohhferta.domain.repositories.coroutines.CoroutineRepository

class OffersRepositoryImpl(
    private val datasource: OffersDatasource,
    private val repository: CoroutineRepository
) : OffersRepository {
    override suspend fun get(): DomainOfferType =
        datasource
            .get()
            .take(2)
            .flowOn(repository.io())
            .map { response ->
                when (response) {
                    is Result.Failure -> Result.Failure(response.failure.toEntity())
                    is Result.Success -> Result.Success(response.success.items.map { it.toEntity() })
                }
            }
}
