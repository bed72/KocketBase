package com.bed.ohhferta.domain.alias

import arrow.core.Either

import com.bed.ohhferta.domain.entities.OfferEntity
import com.bed.ohhferta.domain.entities.FailureEntity

typealias DomainOfferType = Either<FailureEntity, List<OfferEntity>>
