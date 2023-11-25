package com.example.mts_music.data

class Converter {

    private fun convertUserToUserSerialization(user: User): UserSerialization {
        return UserSerialization(user.phoneNumber, user.username, user.sms)
    }

    private fun convertUserSerializationToUser(user: UserSerialization): User {
        return User(user.phoneNumber, user.username, user.sms)
    }

    fun convertRoomToRoomSerialization(room: Room): RoomSerialization{
        val usersSerialization = room.users.map {
            convertUserToUserSerialization(it)
        }
        return RoomSerialization(room.author, usersSerialization, room.access)
    }

    fun convertRoomSerializationToRoom(room: RoomSerialization): Room {
        val users = room.users.map {
            convertUserSerializationToUser(it)
        }
        return Room(room.author, users, room.access)
    }
}