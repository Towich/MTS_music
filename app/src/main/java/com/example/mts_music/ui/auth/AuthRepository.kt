package com.example.mts_music.ui.auth

import com.example.mts_music.API.ApiService

class AuthRepository {

    private val apiService by lazy {
        ApiService.create()
    }
    fun mobileLogin(phoneNumber: String): String {
         val response = apiService.mobileLogin(phoneNumber)
         if(response == 200) {
             return "success"
         }
         return "failure"
    }

    fun smsLogin(code: String): String {
        val response = apiService.smsLogin(code)
        if(response == 200) {
            return "success"
        }
        return "failure"
    }

    fun sendSms() {
        apiService.sendSms()
    }

}