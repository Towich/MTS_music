package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class RoomSerialization(
    val roomName: String,
    val author: String,
    val users: List<UserSerialization>,
    val access: Int
)
