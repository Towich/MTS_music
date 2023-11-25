package com.example.mts_music.ui.profile

import com.example.mts_music.API.ApiService

class ProfileRepository {

    private val apiService by lazy {
        ApiService.create()
    }


}