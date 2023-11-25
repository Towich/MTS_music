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
        route = "rooms_screen",
        title = "Комнаты",
        icon = R.drawable.rooms_icon
    )

    object RoomScreen : Screen(
        route = "room_screen",
        title = "Комната",
        icon = R.drawable.rooms_icon
    )

    object NewRoomScreen : Screen(
        route = "new_room_screen",
        title = "Новая комната",
        icon = R.drawable.rooms_icon
    )

    object Items{
        val list = listOf(
            RoomsScreen, ProfileScreen
        )
    }
}