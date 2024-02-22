package com.bed.ohhferta.data.alias

import kotlinx.coroutines.flow.Flow

import com.bed.ohhferta.domain.result.Result
import com.bed.ohhferta.framework.clients.responses.PageResponse
import com.bed.ohhferta.framework.clients.responses.OfferResponse
import com.bed.ohhferta.framework.clients.responses.FailureResponse

typealias RemoteOfferType = Flow<Result<FailureResponse, PageResponse<OfferResponse>>>
