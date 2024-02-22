package com.bed.ohhferta.domain.alias

import kotlinx.coroutines.flow.Flow

import com.bed.ohhferta.domain.result.Result
import com.bed.ohhferta.domain.entities.OfferEntity
import com.bed.ohhferta.domain.entities.FailureEntity

typealias DomainOfferType = Flow<Result<FailureEntity, List<OfferEntity>>>
