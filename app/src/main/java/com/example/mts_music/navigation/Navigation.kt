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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mts_music.App
import com.example.mts_music.Constants
import com.example.mts_music.ui.auth.AuthScreen
import com.example.mts_music.ui.auth.AuthViewModel
import com.example.mts_music.ui.chat.ChatScreen
import com.example.mts_music.ui.chat.ChatViewModel
import com.example.mts_music.ui.newRoom.NewRoomScreen
import com.example.mts_music.ui.newRoom.NewRoomViewModel
import com.example.mts_music.ui.profile.ProfileScreen
import com.example.mts_music.ui.profile.ProfileViewModel
import com.example.mts_music.ui.room.RoomRepository
import com.example.mts_music.ui.room.RoomScreen
import com.example.mts_music.ui.room.RoomViewModel
import com.example.mts_music.ui.rooms.RoomsScreen
import com.example.mts_music.ui.rooms.RoomsViewModel

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Constants.startScreen)
}

@Composable
fun Navigation(app: App, navController: NavHostController, context: Context, startScreen: Screen) {

    NavHost(
        navController = navController,
        startDestination = startScreen.route,
    ) {
        composable(
            route = Screen.AuthorizationScreen.route
        ) {
            NavigationRouter.currentScreen.value = Screen.AuthorizationScreen
            AuthScreen(
                navController = navController,
                context = context,
                viewModel = viewModel(factory = AuthViewModel.AuthViewModelFactory(context, app.authRepository))
            )
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            NavigationRouter.currentScreen.value = Screen.ProfileScreen
            ProfileScreen(
                navController = navController,
                viewModel = viewModel(factory = ProfileViewModel.ProfileViewModelFactory(context, app.roomRepository,
                    app.profileRepository))
            )
        }
        composable(
            route = Screen.RoomsScreen.route
        ) {
            NavigationRouter.currentScreen.value = Screen.RoomsScreen
            RoomsScreen(
                navController = navController,
                mViewModel = viewModel(factory = RoomsViewModel.RoomsViewModelFactory(context, app.roomRepository))
            )
        }
        composable(
            route = Screen.RoomScreen.route
        ) {
            NavigationRouter.currentScreen.value = Screen.RoomScreen
            RoomScreen(
                navController = navController,
                context = context,
                mViewModel = viewModel(factory = RoomViewModel.RoomViewModelFactory(context, app.roomRepository))
            )
        }
        composable(
            route = Screen.NewRoomScreen.route
        ) {
            NavigationRouter.currentScreen.value = Screen.NewRoomScreen
            NewRoomScreen(
                navController = navController,
                mViewModel = viewModel(factory = NewRoomViewModel.NewRoomViewModelFactory(context, app.roomRepository))
            )
        }
        composable(
            route = Screen.ChatScreen.route
        ) {
            NavigationRouter.currentScreen.value = Screen.ChatScreen
            ChatScreen(
                navController = navController,
                mViewModel = viewModel(factory = ChatViewModel.ChatViewModelFactory(context, app.chatRepository))
            )
        }
    }
}