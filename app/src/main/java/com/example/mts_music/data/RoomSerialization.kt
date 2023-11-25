package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class RoomSerialization(
    val author: String,
    val users: List<UserSerialization>,
    val access: Int
)
