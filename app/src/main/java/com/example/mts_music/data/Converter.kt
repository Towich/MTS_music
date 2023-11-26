package com.example.mts_music.data

class Converter {

    // User
    private fun convertUserToUserSerialization(user: User): UserSerialization {
        return UserSerialization(user.id, user.phoneNumber, user.username)
    }

    fun convertUserSerializationToUser(user: UserSerialization): User {
        return User(user.id, user.phone_number, user.user_name)
    }

    private fun convertPhoneNumberToPhoneNumberSerialization(phoneNumber: PhoneNumber): PhoneNumberSerialization {
        return PhoneNumberSerialization(phoneNumber.phoneNumber)
    }

    private fun convertPhoneNumberSerializationToPhoneNumber(phoneNumber: PhoneNumberSerialization): PhoneNumber {
        return PhoneNumber(phoneNumber.phone_number)
    }


    // Room
    fun convertRoomToRoomSerialization(room: Room): RoomSerialization {
        val usersSerialization = room.users.map {
            convertUserToUserSerialization(it)
        }
        return RoomSerialization(room.id, room.roomName, room.roomToken, room.playingAudio, room.authorAudio, room.authorRoom, usersSerialization, room.access)
    }

    fun convertRoomSerializationToRoom(room: RoomSerialization): Room {
        val users = room.users.map {
            convertUserSerializationToUser(it)
        }
        return Room(room.id, room.roomName, room.roomToken, room.playingAudio,room.authorAudio, room.authorRoom, users, room.access)
    }

    // Code
    fun convertCodeToCodeSerialization(code: Code): CodeSerialization {
        return CodeSerialization(code.code)
    }

    fun convertCodeSerializationToCode(code: CodeSerialization): Code {
        return Code(code.code)
    }
}