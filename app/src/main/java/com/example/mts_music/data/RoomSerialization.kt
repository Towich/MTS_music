package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class RoomSerialization(
    val roomName: String,
    val numberOfUsers: Int,
    val users: List<User>
)
