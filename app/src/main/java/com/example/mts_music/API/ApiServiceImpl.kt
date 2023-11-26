package com.example.mts_music.API

import android.util.Log
import com.example.mts_music.API.ApiRoutes.MUSIC
import com.example.mts_music.API.ApiRoutes.ROOMS
import com.example.mts_music.data.CodeSerialization
import com.example.mts_music.data.NewRoomSerialization
import com.example.mts_music.data.PhoneNumberSerialization
import com.example.mts_music.data.RoomIdNameUser_CountSerialization
import com.example.mts_music.data.RoomUserIdAccessNameSerialization
import com.example.mts_music.data.UserNickSerialization
import com.example.mts_music.data.UserSerialization
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
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
                return response.status.value
            } else {
                Log.d("mobileLogin failed", response.body())
                return  response.status.value
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
                return  UserSerialization(0, "", "")
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun sendUserNick(user: UserSerialization): Int {
        try {
            val userNick = UserNickSerialization(user.user_name)
            val response: HttpResponse = client.put {
                url(ApiRoutes.BASE_URL + ApiRoutes.USER + "/" + user.id)
                setBody(userNick)
            }

            if (response.status.isSuccess()) {
                return response.status.value
            } else {
                Log.d("smsLogin failed", response.body())
                return  response.status.value
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getListOfRooms(): List<RoomIdNameUser_CountSerialization> {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ROOMS)
            }
            if (response.status.isSuccess()) {
                return response.body()
            } else {
                throw Exception("Request failed with status: ${response.status.value}")
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun postNewRoom(room: RoomUserIdAccessNameSerialization): NewRoomSerialization {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ROOMS)
                setBody(room)
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

    override suspend fun getMusic(room_id: String, user_id: String) {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + MUSIC + "/" + room_id + "/" + "1")
            }
        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun deleteRoom(roomId: String, userId: String?) {
        try {
            val response: HttpResponse = client.delete {
                url(ApiRoutes.BASE_URL + ROOMS + "/" + roomId + "/" + userId)
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