package com.example.mts_music.ui.room

import android.content.Context
import android.media.MediaPlayer
import com.example.mts_music.API.ApiService
import com.example.mts_music.R
import com.example.mts_music.data.Room

class RoomRepository(private val context: Context){
    private var currentRoom: Room? = null
    private val apiService by lazy {
        ApiService.create()
    }
    private val mp: MediaPlayer = MediaPlayer.create(context, R.raw.zveri)
    fun setCurrentRoom(newRoom: Room){
        currentRoom = newRoom
    }
    fun getCurrentRoom(): Room? = currentRoom

    fun getMediaPlayer(): MediaPlayer = mp

    fun isTokenValid(token: String): Boolean {
        val room = currentRoom ?: return false
        return room.roomToken == token
    }
}