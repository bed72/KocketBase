package com.bed.ohhferta.domain.entities

import java.time.LocalDate

data class OfferEntity(
    val id: String,
    val name: String,
    val price: Float,
    val store: String,
    val thumbnail: String,
    val validate: LocalDate,
    val description: String,
    val images: List<String>,
    val categories: List<String>,
)
