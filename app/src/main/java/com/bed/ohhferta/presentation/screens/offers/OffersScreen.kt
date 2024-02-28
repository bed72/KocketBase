package com.bed.ohhferta.presentation.screens.offers

import kotlinx.coroutines.delay

import coil.compose.rememberAsyncImagePainter

import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign

import com.bed.ohhferta.R

import com.bed.ohhferta.domain.entities.OfferEntity
import com.bed.ohhferta.domain.entities.FailureEntity

import com.bed.ohhferta.presentation.shared.states.States
import java.util.Locale

@Composable
fun OffersScreen(viewModel: OffersViewModel) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .wrapContentSize(Alignment.Center)
    ) {
        when (state) {
            States.Initial, States.Loading -> IsLoading()
            is States.Failure -> IsFailure((state as States.Failure<FailureEntity>).failure)
            is States.Success -> IsSuccessful((state as States.Success<List<OfferEntity>>).success)
        }
    }
}

@Composable
private fun IsLoading() {
    CircularProgressIndicator()
}

@Composable
private fun IsFailure(failure: FailureEntity) {
    Text(
        text = failure.message,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun IsSuccessful(offers: List<OfferEntity>) {
    val texts = listOf("Ohh Ferta")
    var textIndex by remember {
        mutableIntStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }

    @Suppress("MagicNumber")
    LaunchedEffect(
        key1 = texts,
    ) {
        while (textIndex < texts.size) {
            texts[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = texts[textIndex]
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(260)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
        }
    }

    Column {
        Text(
            text = textToDisplay,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(16.dp)

        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(
                items = offers,
                key = { it.id }
            ) { offer ->
                Card(offer = offer)
            }
        }
    }
}

@Composable
private fun Card(offer: OfferEntity) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxSize()
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    ),
                painter = rememberAsyncImagePainter(model = offer.thumbnail),
                contentDescription = stringResource(id = R.string.image_offer_description, offer.name)
            )

            Text(
                text = offer.validate.dayOfWeek.toString().lowercase(Locale.ROOT),
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light,
                )
            )

            Text(
                text = offer.name,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )


//                HtmlText(
//                    maxLines = 6,
//                    text = offer.description,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.padding(8.dp),
//                    style = MaterialTheme.typography.bodyMedium,
//                )
        }
    }
}
