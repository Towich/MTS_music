package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class RoomUserIdAccessNameSerialization(
    val user_id: Int,
    val name: String,
    val access: Int,
    val token: String
)
