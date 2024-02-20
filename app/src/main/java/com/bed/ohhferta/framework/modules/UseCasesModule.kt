package com.bed.ohhferta.framework.modules

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.factoryOf

import com.bed.ohhferta.domain.usecases.offers.GetAllOffersUseCase
import com.bed.ohhferta.domain.usecases.offers.GetAllOffersUseCaseImpl

import com.bed.ohhferta.domain.usecases.coroutines.CoroutinesUseCase
import com.bed.ohhferta.domain.usecases.coroutines.CoroutinesUseCaseImpl

val useCasesModule = module {
    singleOf(::CoroutinesUseCaseImpl) bind CoroutinesUseCase::class
    factoryOf(::GetAllOffersUseCaseImpl) bind GetAllOffersUseCase::class
}
