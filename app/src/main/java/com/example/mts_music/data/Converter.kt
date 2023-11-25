package com.example.mts_music.data

import com.google.gson.Gson

class Converter {
    private val gson = Gson()

    fun convertUserToUserSerialization(user: User): UserSerialization {
        return UserSerialization(user.phoneNumber, user.username, user.sms)
    }

    fun convertUserSerializationToJson(user: UserSerialization): String {
        return gson.toJson(user)
    }

    fun convertJsonToUserSerialization(jsonString: String): UserSerialization {
        return gson.fromJson(jsonString, UserSerialization::class.java)
    }

    fun convertUserSerializationToUser(user: UserSerialization): User {
        return User(user.phoneNumber, user.username, user.sms)
    }
}