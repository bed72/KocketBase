package com.bed.ohhferta.framework.modules

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.factoryOf

import com.bed.ohhferta.data.repositories.OffersRepositoryImpl
import com.bed.ohhferta.domain.repositories.offers.OffersRepository

import com.bed.ohhferta.domain.repositories.coroutines.CoroutineRepository
import com.bed.ohhferta.domain.repositories.coroutines.CoroutineRepositoryImpl

val repositoriesModule = module {
    factoryOf(::OffersRepositoryImpl) bind OffersRepository::class
    factoryOf(::CoroutineRepositoryImpl) bind CoroutineRepository::class
}
