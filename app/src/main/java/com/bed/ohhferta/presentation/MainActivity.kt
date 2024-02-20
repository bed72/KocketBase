package com.bed.ohhferta.presentation

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.Modifier

import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.bed.ohhferta.presentation.components.RootComponent
import com.bed.ohhferta.presentation.shared.routes.RootRoutes

import com.bed.ohhferta.presentation.themes.OhhFertaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            OhhFertaTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    RootComponent(
                        navController = rememberNavController(),
                        startDestination = RootRoutes.Home.GRAPH
                    )
                }
            }
        }
    }
}
