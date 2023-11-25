package com.example.mts_music.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.ui.room.RoomRepository

class ProfileViewModel(context: Context, private val repository: RoomRepository) : ViewModel() {

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

    fun exitFromAccount(){

    }

    class ProfileViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ProfileViewModel(context, repository) as T
    }
}