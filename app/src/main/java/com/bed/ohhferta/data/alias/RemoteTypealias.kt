package com.bed.ohhferta.data.alias

import arrow.core.Either

import com.bed.ohhferta.framework.clients.responses.PageResponse
import com.bed.ohhferta.framework.clients.responses.OfferResponse
import com.bed.ohhferta.framework.clients.responses.FailureResponse

typealias RemoteOfferType = Either<FailureResponse, PageResponse<OfferResponse>>
