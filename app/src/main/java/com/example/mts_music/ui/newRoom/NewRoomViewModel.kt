package com.example.mts_music.ui.newRoom

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.Room
import com.example.mts_music.ui.auth.AuthRepository
import com.example.mts_music.ui.auth.AuthViewModel
import com.example.mts_music.ui.room.RoomRepository

class NewRoomViewModel(context: Context, private val repository: RoomRepository) : ViewModel() {

    fun setCurrentRoom(newRoom: Room) = repository.setCurrentRoom(newRoom)
    fun getCurrentRoom(): Room {
        return repository.getCurrentRoom() ?: Room()
    }

    class NewRoomViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = NewRoomViewModel(context, repository) as T
    }
}