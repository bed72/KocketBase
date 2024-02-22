package com.bed.ohhferta.framework.clients.responses

import java.time.LocalDate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.ohhferta.domain.entities.OfferEntity
import com.bed.ohhferta.framework.clients.paths.Paths

@Serializable
data class OfferResponse(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("price")
    val price: Float,

    @SerialName("store")
    val store: String,

    @SerialName("description")
    val description: String,

    @SerialName("collectionName")
    val collectionName: String,

    @SerialName("validate")
    val validate: String,

    @SerialName("images")
    val images: List<String>,

    @SerialName("categories")
    val categories: List<String>,
)

fun OfferResponse.toEntity() = OfferEntity(
    id,
    name,
    price,
    store,
    thumbnail = thumbnail(),
    validate = LocalDate.now(),
    description,
    images,
    categories
)

private fun OfferResponse.validate() = LocalDate.parse(validate)

private fun OfferResponse.thumbnail() =
    "${Paths.API.value}/api/files/$collectionName/$id/${images.first()}?thumb=0x300"
