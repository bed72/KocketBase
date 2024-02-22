package com.bed.ohhferta.domain.repositories.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineRepository {
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

class CoroutineRepositoryImpl : CoroutineRepository
