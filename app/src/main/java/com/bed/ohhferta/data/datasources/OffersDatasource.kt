package com.bed.ohhferta.data.datasources

import com.bed.ohhferta.data.alias.RemoteOfferType

interface OffersDatasource {
    suspend fun getAll(vararg parameters: Pair<String, Any>?): RemoteOfferType
}
