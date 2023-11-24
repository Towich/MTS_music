package com.example.mts_music.ui.profile

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private var username = ""

    fun getUsername(): String {
        return username
    }

    fun setUsername(newUsername: String) {
        username = newUsername
    }

    fun isUsernameCorrect(username: String): Boolean {
        if (username.isEmpty()) return false
        return true
    }
}