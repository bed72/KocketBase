package com.bed.ohhferta.framework.modules

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.factoryOf

import com.bed.ohhferta.data.datasources.OffersDatasource

import com.bed.ohhferta.framework.datasources.remote.RemoteOffersDatasource

val datasourceModule = module {
    factoryOf(::RemoteOffersDatasource) bind OffersDatasource::class
}
