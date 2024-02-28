package com.bed.ohhferta.presentation.screens.offers

import kotlinx.coroutines.delay

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.systemBarsPadding

import com.bed.ohhferta.domain.entities.OfferEntity
import com.bed.ohhferta.domain.entities.FailureEntity

import com.bed.ohhferta.presentation.shared.states.States

import com.bed.ohhferta.presentation.components.FailureComponent
import com.bed.ohhferta.presentation.components.LoadingComponent

@Composable
fun OffersScreen(viewModel: OffersViewModel) {
    val texts = listOf("Ohh Ferta")

    val state by viewModel.state.collectAsState()

    var textIndex by remember { mutableIntStateOf(0) }
    var textToDisplay by remember { mutableStateOf("") }

    @Suppress("MagicNumber")
    LaunchedEffect(key1 = texts) {
        while (textIndex < texts.size) {
            texts[textIndex].forEachIndexed { index, _ ->
                textToDisplay = texts[textIndex].substring(startIndex = 0, endIndex = index + 1)
                delay(260)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .wrapContentSize(Alignment.Center)
    ) {
        when (state) {
            States.Initial, States.Loading -> LoadingComponent()
            is States.Failure -> FailureComponent((state as States.Failure<FailureEntity>).failure)
            is States.Success ->
                OfferSuccessfulComponent(textToDisplay, (state as States.Success<List<OfferEntity>>).success)
        }
    }
}



