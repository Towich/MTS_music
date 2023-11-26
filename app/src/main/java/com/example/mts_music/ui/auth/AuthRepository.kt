package com.example.mts_music.ui.auth

import com.example.mts_music.API.ApiService
import com.example.mts_music.data.CodeSerialization
import com.example.mts_music.data.PhoneNumberSerialization
import com.example.mts_music.data.UserSerialization

class AuthRepository {

    private val apiService by lazy {
        ApiService.create()
    }
    suspend fun mobileLogin(phoneNumber: String): String {
         val response = apiService.mobileLogin(PhoneNumberSerialization(phoneNumber))
         if(response == 200) {
             return "success"
         }
         return "failure"
    }

    suspend fun smsLogin(code: String): UserSerialization {
        return apiService.smsLogin(CodeSerialization(code))
    }

    suspend fun sendSms(phoneNumber: String) {
        val response = apiService.mobileLogin(PhoneNumberSerialization(phoneNumber))
    }

}