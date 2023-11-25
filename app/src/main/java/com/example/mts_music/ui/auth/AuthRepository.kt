package com.example.mts_music.ui.auth

import com.example.mts_music.API.ApiService

class AuthRepository {

    private val apiService by lazy {
        ApiService.create()
    }
    fun mobileLogin(phoneNumber: String) {
        // val response = apiService.mobileLogin(phoneNumber)
        // if(response == 200) {
        //  return "access"
        // }
        // return "failure"
    }

}