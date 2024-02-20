package com.bed.ohhferta.framework.modules

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

import com.bed.ohhferta.framework.clients.HttpClient
import com.bed.ohhferta.framework.clients.HttpClientImpl

fun clientsModule() = module {
    singleOf(::HttpClientImpl) bind HttpClient::class
}
