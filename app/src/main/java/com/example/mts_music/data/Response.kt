package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class Response (
    val user: UserSerialization
)