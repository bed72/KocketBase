package com.bed.ohhferta.domain.usecases.offers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import com.bed.ohhferta.domain.alias.DomainOfferType
import com.bed.ohhferta.domain.repositories.OffersRepository

import com.bed.ohhferta.domain.usecases.UseCaseWithoutParameter
import com.bed.ohhferta.domain.usecases.coroutines.CoroutinesUseCase

interface GetAllOffersUseCase {
    operator fun invoke(): Flow<DomainOfferType>
}

class GetAllOffersUseCaseImpl(
    private val useCase: CoroutinesUseCase,
    private val repository: OffersRepository,
) : GetAllOffersUseCase, UseCaseWithoutParameter<DomainOfferType>() {
    override suspend fun doWork(): DomainOfferType =
        withContext(useCase.io()) { repository.getAll(null) }
}
