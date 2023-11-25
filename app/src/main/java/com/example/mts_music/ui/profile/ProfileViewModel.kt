package com.example.mts_music.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mts_music.Constants.ID
import com.example.mts_music.Constants.PHONENUMBER
import com.example.mts_music.SharedPreferences
import com.example.mts_music.ui.room.RoomRepository
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context, private val repository: RoomRepository, private val profileRepository: ProfileRepository) : ViewModel() {

    private var username = ""
    val sharedPreference: SharedPreferences = SharedPreferences(context = context)

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

    fun exitFromAccount() = viewModelScope.launch {
        sharedPreference.clearSharedPreference()
    }

    suspend fun sendUserNick(usernameText: String) {
        profileRepository.sendUserNick(usernameText, sharedPreference.getValueString(PHONENUMBER)!!,
            sharedPreference.getValueInt(ID)!!)
    }


    class ProfileViewModelFactory(private val context: Context, private val repository: RoomRepository, private val profileRepository: ProfileRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ProfileViewModel(context, repository, profileRepository) as T
    }
}