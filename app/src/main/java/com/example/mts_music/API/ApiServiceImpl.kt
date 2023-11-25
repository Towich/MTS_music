package com.example.mts_music.API

import io.ktor.client.HttpClient

class ApiServiceImpl(private val client: HttpClient): ApiService {
    override fun mobileLogin(phoneNumber: String): Int {
        return 0
    }

    override fun smsLogin(code: String): Int {
        return 0
    }

    override fun sendSms(): Int {
        return 0
    }

}