package com.example.mts_music.data

class Converter {
//    private val json = Json { encodeDefaults = true }

    fun convertUserToUserSerialization(user: User): UserSerialization {
        return UserSerialization(user.phoneNumber, user.username, user.sms)
    }

//    fun convertUserSerializationToJson(user: UserSerialization): String {
//        return json.encodeToString(user)
//    }

//    fun convertJsonToUserSerialization(jsonString: String): UserSerialization {
//        return json.decodeFromString<UserSerialization>(jsonString)
//    }

    fun convertUserSerializationToUser(user: UserSerialization): User {
        return User(user.phoneNumber, user.username, user.sms)
    }
}