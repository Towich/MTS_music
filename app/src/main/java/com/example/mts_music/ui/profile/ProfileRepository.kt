package com.example.mts_music.ui.profile

import android.util.Log
import com.example.mts_music.API.ApiService
import com.example.mts_music.data.UserSerialization

class ProfileRepository {

    private val apiService by lazy {
        ApiService.create()
    }
    suspend fun sendUserNick(usernameText: String, phoneNumber: String, id: Int) {
        val result = apiService.sendUserNick(UserSerialization(id, phoneNumber, usernameText))
        Log.d("result of sendUserNick()", result.toString())
    }
}