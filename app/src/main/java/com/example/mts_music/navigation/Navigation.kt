package com.example.mts_music.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mts_music.ui.auth.AuthScreen

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screen.AuthorizationScreen.route) {
        composable(route = Screen.AuthorizationScreen.route) {
            AuthScreen(navController = navController)
        }
    }
}