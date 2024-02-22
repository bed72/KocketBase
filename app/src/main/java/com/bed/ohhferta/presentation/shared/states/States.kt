package com.bed.ohhferta.presentation.shared.states


sealed class States<out F, out S> {
    data object Initial : States<Nothing, Nothing>()
    data object Loading : States<Nothing, Nothing>()
    data class Success<S>(val success: S) : States<Nothing, S>()
    data class Failure<F>(val failure: F) : States<F, Nothing>()
}
