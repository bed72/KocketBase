package com.bed.ohhferta.presentation.components

import androidx.compose.runtime.Composable

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

import com.bed.ohhferta.domain.entities.FailureEntity

@Composable
fun FailureComponent(failure: FailureEntity) {
    Text(
        text = failure.message,
        style = MaterialTheme.typography.titleLarge
    )
}
