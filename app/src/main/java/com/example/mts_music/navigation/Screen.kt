package com.example.mts_music.navigation

import androidx.compose.material.icons.Icons
import com.example.mts_music.R

sealed class Screen(val route : String, val title : String, val icon: Int) {
    object AuthorizationScreen : Screen(
        route = "authorization_screen",
        title = "Авторизация",
        icon = R.drawable.rooms_icon
    )

    object ProfileScreen : Screen(
        route = "profile_screen",
        title = "Профиль",
        icon = R.drawable.profile_user
    )

    object RoomsScreen : Screen(
        route = "room_screen",
        title = "Комнаты",
        icon = R.drawable.rooms_icon
    )

    object Items{
        val list = listOf(
            RoomsScreen, ProfileScreen
        )
    }
}