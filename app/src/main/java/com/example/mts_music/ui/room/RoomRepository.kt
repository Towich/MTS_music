package com.example.mts_music.ui.room

import com.example.mts_music.data.Room

class RoomRepository {
    private var currentRoom: Room? = null

    fun setCurrentRoom(newRoom: Room){
        currentRoom = newRoom
    }

    fun getCurrentRoom(): Room? = currentRoom
}