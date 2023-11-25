package com.example.mts_music

import android.app.Application
import com.example.mts_music.ui.auth.AuthRepository
import com.example.mts_music.ui.room.RoomRepository

class App: Application() {

    val authRepository by lazy { AuthRepository()}
    val roomRepository by lazy { RoomRepository()}
}