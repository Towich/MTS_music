package com.example.mts_music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mts_music.navigation.Navigation
import com.example.mts_music.navigation.NavigationRouter
import com.example.mts_music.navigation.Screen
import com.example.mts_music.ui.theme.MTS_musicTheme
import com.example.mts_music.ui.bottomNavigation.CustomBottomNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MTS_musicTheme {
                Scaffold(
                    bottomBar = {
                        when (NavigationRouter.currentScreen.value) {
                            Screen.AuthorizationScreen, Screen.RoomScreen -> null
                            else -> {
                                CustomBottomNavigation(currentScreenRoute = NavigationRouter.currentScreen.value.route) { screen ->
                                    if (screen.route != NavigationRouter.currentScreen.value.route) {
                                        NavigationRouter.currentScreen.value = screen
                                        navController.navigate(screen.route)
                                    }
                                }
                            }
                        }
                    }
                ) { contentPadding ->
                    run {
                        Box(modifier = Modifier.padding(contentPadding)) {
                            Navigation(navController, applicationContext)
                        }
                    }
                }
            }
        }
    }
}
