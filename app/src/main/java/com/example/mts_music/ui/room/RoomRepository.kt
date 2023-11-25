package com.example.mts_music.ui.room

import com.example.mts_music.API.ApiService
import com.example.mts_music.data.Room

class RoomRepository {

    private val apiService by lazy {
        ApiService.create()
    }

    var currentRoom: Room? = null


}