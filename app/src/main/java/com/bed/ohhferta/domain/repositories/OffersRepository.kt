package com.bed.ohhferta.domain.repositories

import com.bed.ohhferta.domain.alias.DomainOfferType

interface OffersRepository {
    suspend fun getAll(vararg parameters: Pair<String, Any>?): DomainOfferType
}
