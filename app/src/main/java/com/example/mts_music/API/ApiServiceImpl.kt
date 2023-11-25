package com.example.mts_music.API

import io.ktor.client.HttpClient

class ApiServiceImpl(private val client: HttpClient): ApiService {
    override fun mobileLogin(phoneNumber: String): Int {
        TODO("Not yet implemented")
    }

    override fun smsLogin(code: String): Int {
        TODO("Not yet implemented")
    }

    override fun sendSms(): Int {
        TODO("Not yet implemented")
    }

}