package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class UserSerialization(
    val phoneNumber: String,
    val username: String,
    val sms: String
)
