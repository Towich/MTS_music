package com.example.mts_music

import android.app.Application
import com.example.mts_music.ui.auth.AuthRepository

class App: Application() {

    val authRepository by lazy { AuthRepository()}
}