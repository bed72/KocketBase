package com.bed.ohhferta.framework.modules

import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModelOf

import com.bed.ohhferta.presentation.screens.offers.OffersViewModel

val viewModelsModule = module {
    viewModelOf(::OffersViewModel)
}
