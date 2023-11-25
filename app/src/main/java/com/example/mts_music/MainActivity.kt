package com.example.mts_music

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.example.mts_music.ui.bottomNavigation.CustomBottomNavigation
import com.example.mts_music.ui.theme.MTS_musicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = applicationContext as App
        var flagStart = false

        val appLinkAction: String? = intent.action
        val appLinkData: Uri? = intent.data

        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            val path: String? = appLinkData.path

            if (path != null && path.contains("room_screen")) {
                val token: String? = appLinkData.getQueryParameter("token")
                val id: String? = appLinkData.getQueryParameter("id")
//                Log.i("DataURLRoom", "Data: $token, $id")
                if (!token.isNullOrEmpty() && app.roomRepository.isTokenValid(token)) {
                    flagStart = true
                }
//                NavigationRouter.currentScreen.value = Screen.RoomScreen
            }
        }

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
                            val startScreen = if (flagStart) Screen.RoomScreen else Screen.AuthorizationScreen
                            Navigation(app, navController, applicationContext, startScreen)
                        }
                    }
                }
            }
        }
    }
}
