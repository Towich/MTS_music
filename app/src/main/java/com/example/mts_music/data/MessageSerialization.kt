package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class MessageSerialization (
    val author: RoomIdNameUser_CountSerialization,
    val text: String
)