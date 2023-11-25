package com.example.mts_music.API

import android.util.Log
import com.example.mts_music.data.Code
import com.example.mts_music.data.CodeSerialization
import com.example.mts_music.data.PhoneNumber
import com.example.mts_music.data.PhoneNumberSerialization
import com.example.mts_music.data.UserIdNickSerialization
import com.example.mts_music.data.UserSerialization
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class ApiServiceImpl(private val client: HttpClient): ApiService {
    override suspend fun mobileLogin(phoneNumber: PhoneNumberSerialization): Int {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.LOGIN)
                setBody(phoneNumber)
            }

            if (response.status.isSuccess()) {
                return response.body()
            } else {
                Log.d("mobileLogin failed", response.body())
                return  response.body()  //!!
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun smsLogin(code: CodeSerialization): UserSerialization {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.LOGIN + ApiRoutes.CHECK_SMS)
                setBody(code)
            }

            if (response.status.isSuccess()) {
                return response.body()
            } else {
                Log.d("smsLogin failed", response.body())
                return  response.body()  //!!
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun sendSms(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun sendUserNick(user: UserSerialization): Int {
        try {
            val userNick = UserIdNickSerialization(user.id, user.username)
            val response: HttpResponse = client.put {
                url(ApiRoutes.BASE_URL + ApiRoutes.USER)
                setBody(userNick)
            }

            if (response.status.isSuccess()) {
                return response.body()
            } else {
                Log.d("smsLogin failed", response.body())
                return  response.body()  //!!
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

}