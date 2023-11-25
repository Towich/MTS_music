package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class UserIdNickSerialization (
    val id: Int,
    val username: String,
)