package com.bed.ohhferta.domain.repositories.offers

import com.bed.ohhferta.domain.alias.DomainOfferType

interface OffersRepository {
    suspend fun get(): DomainOfferType
}
