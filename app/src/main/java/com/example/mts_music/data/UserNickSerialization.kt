package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class UserNickSerialization (
    val user_name: String,
)