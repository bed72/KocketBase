package com.bed.ohhferta.presentation.shared.routes

import androidx.annotation.StringRes

import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.FavoriteBorder

import com.bed.ohhferta.R

class RootRoutes {
    object Home {
        const val GRAPH = "home_graph"
        const val OFFERS_SCREEN = "offers_screen"
        const val STORES_SCREEN = "stores_screen"
        const val FAVORITES_SCREEN = "favorites_screen"
    }
}

sealed class BottomNavigationRoutes(val route: String, val icon: ImageVector, @StringRes val label: Int) {
    data object Offers :
        BottomNavigationRoutes(
            RootRoutes.Home.OFFERS_SCREEN,
            Icons.Filled.AccessTime,
            R.string.offers_title_tab
        )
    data object Stores :
        BottomNavigationRoutes(
            RootRoutes.Home.STORES_SCREEN,
            Icons.Filled.Storefront,
            R.string.stores_title_tab
        )
    data object Favorites :
        BottomNavigationRoutes(
            RootRoutes.Home.FAVORITES_SCREEN,
            Icons.Filled.FavoriteBorder,
            R.string.favorites_title_tab
        )
}
