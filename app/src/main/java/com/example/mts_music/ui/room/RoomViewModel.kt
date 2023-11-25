package com.example.mts_music.ui.room

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.Room
import com.example.mts_music.ui.rooms.RoomsViewModel

class RoomViewModel(context: Context, private val repository: RoomRepository): ViewModel() {

    fun getCurrentRoom(): Room {
        return repository.getCurrentRoom() ?: Room()
    }

    fun getMediaPlayer(): MediaPlayer = repository.getMediaPlayer()

    class RoomViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RoomViewModel(context, repository) as T
    }
}