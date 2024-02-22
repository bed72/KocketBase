package com.bed.ohhferta.presentation.screens.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

import com.bed.ohhferta.presentation.shared.states.States

import com.bed.ohhferta.domain.result.Result
import com.bed.ohhferta.domain.entities.OfferEntity
import com.bed.ohhferta.domain.entities.FailureEntity

import com.bed.ohhferta.domain.repositories.offers.OffersRepository
import com.bed.ohhferta.domain.repositories.coroutines.CoroutineRepository

class OffersViewModel(
    private val offersRepository: OffersRepository,
    private val coroutineRepository: CoroutineRepository
) : ViewModel() {
    private val _state = MutableStateFlow<States<FailureEntity, List<OfferEntity>>>(States.Initial)
    val state: StateFlow<States<FailureEntity, List<OfferEntity>>> get() = _state.asStateFlow()

    init { getAll() }

    private fun getAll() {
        _state.update { States.Loading }

        viewModelScope.launch(coroutineRepository.default()) {
            when (val response = offersRepository.get()) {
                is Result.Failure -> _state.update { States.Failure(response.failure) }
                is Result.Success -> _state.update { States.Success(response.success) }
            }
//            offersRepository
//                .get()
////                .flowOn(coroutineRepository.main())
//                .collect { response ->
//                    when (response) {
//                        is Result.Failure -> _state.update { States.Failure(response.failure) }
//                        is Result.Success -> _state.update { States.Success(response.success) }
//                    }
//                }
        }
    }
}
