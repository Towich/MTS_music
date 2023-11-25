package com.example.mts_music.ui.rooms

import androidx.lifecycle.ViewModel
import com.example.mts_music.data.Room
import com.example.mts_music.data.User

class RoomsViewModel: ViewModel() {
    val rooms: List<Room> = listOf(
        Room(
            roomName = "Комната №1",
            author = "Мария",
            users = listOf(User("+71234567890", "Мария"), User("+75678904637", "Никита"), User("+75647890329", "Иван")),
            access = 1
        ),
        Room(
            roomName = "Комната МусиМисиМуси МУси МИссси Муси смсимисми",
            author = "Никита",
            users = listOf(User("+75678904637", "Никита"), User("+75647890329", "Михаил")),
            access = 1
        ),
        Room(
            roomName = "Комната Никиты",
            author = "Никита",
            users = listOf(User("+75678904637", "Никита")),
            access = 1
        ),
        Room(
            roomName = "Комната Муси",
            author = "Иван",
            users = listOf(User("+75678904637", "Иван"), User("+75647890329", "Мария")),
            access = 1
        ),
        Room(
            roomName = "Комната №254",
            author = "Михаил",
            users = listOf(User("+71234567890", "Мария"), User("+75678904637", "Никита"), User("+75647890329", "Иван")),
            access = 1
        ),
    )
}