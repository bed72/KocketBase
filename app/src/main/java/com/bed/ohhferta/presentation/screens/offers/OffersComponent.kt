package com.bed.ohhferta.presentation.screens.offers

import java.util.Locale

import coil.compose.rememberAsyncImagePainter

import androidx.compose.runtime.Composable

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid

import com.bed.ohhferta.R

import com.bed.ohhferta.domain.entities.OfferEntity

@Composable
fun OfferSuccessfulComponent(text: String, offers: List<OfferEntity>) {

    Column {
        Text(
            text = text,
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
                OfferCardComponent(offer = offer)
            }
        }
    }
}

@Composable
private fun OfferCardComponent(offer: OfferEntity) {
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
        }
    }
}
