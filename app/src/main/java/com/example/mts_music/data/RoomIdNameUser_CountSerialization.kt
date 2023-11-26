package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class RoomIdNameUser_CountSerialization(
    val id: Int,
    val name: String,
    val users_count: Int
)
