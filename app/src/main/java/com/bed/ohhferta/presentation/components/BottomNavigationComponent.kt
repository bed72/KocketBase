package com.bed.ohhferta.presentation.components

import android.annotation.SuppressLint

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable

import androidx.navigation.navigation
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.ui.res.stringResource

import androidx.navigation.compose.currentBackStackEntryAsState

import com.bed.ohhferta.presentation.shared.routes.RootRoutes

import com.bed.ohhferta.presentation.screens.offers.offersScreen
import com.bed.ohhferta.presentation.screens.stores.storesScreen
import com.bed.ohhferta.presentation.screens.favorites.favoritesScreen

import com.bed.ohhferta.presentation.shared.routes.BottomNavigationRoutes

@Composable
fun BottomNavigationComponent(navController: NavController) {
    val items = listOf(
        BottomNavigationRoutes.Offers,
        BottomNavigationRoutes.Stores,
        BottomNavigationRoutes.Favorites
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                alwaysShowLabel = false,

                selected = currentRoute == screen.route,
                icon = { Icon(screen.icon, contentDescription = null) },

                label = { Text(text = stringResource(screen.label), style = MaterialTheme.typography.bodySmall) },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun RootComponent(navController: NavHostController, startDestination: String) {
    Scaffold(
        bottomBar = {
            BottomNavigationComponent(
                navController = navController
            )
        }
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            navigation(
                route = RootRoutes.Home.GRAPH,
                startDestination = RootRoutes.Home.OFFERS_SCREEN
            ) {
                offersScreen()
                storesScreen()
                favoritesScreen()
            }
        }
    }
}

