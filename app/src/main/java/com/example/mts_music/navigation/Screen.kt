package com.example.mts_music.navigation

import com.example.mts_music.R

sealed class Screen(val route : String, val title : String, val icon: Int) {
    object AuthorizationScreen : Screen(
        route = "authorization_screen",
        title = "Authorization",
        icon = R.drawable.conference_room_svgrepo_com_1
    )

    object ProfileScreen : Screen(
        route = "profile_screen",
        title = "Profile",
        icon = R.drawable.conference_room_svgrepo_com_1
    )
}