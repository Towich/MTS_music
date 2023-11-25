package com.example.mts_music.ui.rooms

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.Room
import com.example.mts_music.data.User
import com.example.mts_music.ui.room.RoomRepository
import com.example.mts_music.ui.room.RoomViewModel

class RoomsViewModel(context: Context, private val repository: RoomRepository): ViewModel() {
    val rooms: List<Room> = listOf(
        Room(
            id = "0001",
            roomName = "Комната №1",
            playingAudio = "О Боже какой мужчина па павв авава",
            authorRoom = "Мария",
            users = listOf(User("+71234567890", "Мария"), User("+75678904637", "Никита"), User("+75647890329", "Иван")),
            access = 1
        ),
        Room(
            id = "0002",
            roomName = "Комната МусиМисиМуси МУси МИссси Муси смсимисми",
            authorRoom = "Никита",
            users = listOf(User("+75678904637", "Никита"), User("+75647890329", "Михаил")),
            access = 1
        ),
        Room(
            id = "0003",
            roomName = "Комната Никиты",
            authorRoom = "Никита",
            users = listOf(User("+75678904637", "Никита")),
            access = 1
        ),
        Room(
            id = "0004",
            roomName = "Комната Муси",
            authorRoom = "Иван",
            users = listOf(User("+75678904637", "Иван"), User("+75647890329", "Мария")),
            access = 1
        ),
        Room(
            id = "0005",
            roomName = "Комната №254",
            authorRoom = "Михаил",
            users = listOf(User("+71234567890", "Мария"), User("+75678904637", "Никита"), User("+75647890329", "Иван")),
            access = 1
        ),
    )

    fun setCurrentRoom(newRoom: Room) = repository.setCurrentRoom(newRoom)

    class RoomsViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RoomsViewModel(context, repository) as T
    }

}