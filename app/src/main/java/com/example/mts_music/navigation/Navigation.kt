package com.example.mts_music.navigation

import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mts_music.Constants
import com.example.mts_music.ui.auth.AuthScreen
import com.example.mts_music.ui.profile.ProfileScreen
import com.example.mts_music.ui.rooms.RoomsScreen

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Constants.startScreen)
}

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = Constants.startScreen.route,
    ) {
        composable(
            route = Screen.AuthorizationScreen.route
        ) {
            AuthScreen(navController = navController, context = context)
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.RoomsScreen.route
        ) {
            RoomsScreen(navController = navController)
        }
    }
}