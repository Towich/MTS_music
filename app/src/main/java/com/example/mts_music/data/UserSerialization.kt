package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class UserSerialization(
    val id: Int,
    val phone_number: String,
    val user_name: String,
)
