package com.bed.ohhferta.domain.result

sealed class Result<out F, out S> {
    data class Failure<out F>(val failure: F) : Result<F, Nothing>()
    data class Success<out S>(val success: S) : Result<Nothing, S>()

    override fun toString() = when (this) {
        is Success<*> -> "Success: [$success]"
        is Failure<*> -> "Failure: [$failure]"
    }
}
