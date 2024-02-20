package com.bed.ohhferta.framework.clients.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.ohhferta.domain.entities.FailureEntity

@Serializable
data class FailureResponse(
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String
)

fun FailureResponse.toEntity() = FailureEntity(code, message)
