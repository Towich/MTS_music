package com.example.mts_music.ui.room

import com.example.mts_music.API.ApiService
import com.example.mts_music.data.Room

class RoomRepository {
    private var currentRoom: Room? = null
    private val apiService by lazy {
        ApiService.create()
    }

    fun setCurrentRoom(newRoom: Room){
        currentRoom = newRoom
    }

    fun getCurrentRoom(): Room? = currentRoom

    fun isTokenValid(token: String): Boolean {
        val room = currentRoom ?: return false
        return room.roomToken == token
    }
}