package com.bed.ohhferta.framework.datasources.remote

import io.ktor.http.HttpMethod
import io.ktor.client.request.url

import com.bed.ohhferta.data.alias.RemoteOfferType
import com.bed.ohhferta.data.datasources.OffersDatasource

import com.bed.ohhferta.framework.clients.request
import com.bed.ohhferta.framework.clients.HttpClient
import com.bed.ohhferta.framework.clients.paths.Paths

import com.bed.ohhferta.framework.clients.responses.PageResponse
import com.bed.ohhferta.framework.clients.responses.OfferResponse
import com.bed.ohhferta.framework.clients.responses.FailureResponse

class RemoteOffersDatasource(private val client: HttpClient) : OffersDatasource {
    override suspend fun get(): RemoteOfferType =
        client.http.request<FailureResponse, PageResponse<OfferResponse>> {
            method = HttpMethod.Get
            url(Paths.OFFERS.value)
        }
}
