package com.example.mts_music.data

data class ChatMessage(
    val id: String = "error",
    val message: String = "error",
    val author: String = "error",
    val isMine: Boolean = false,
    val timestamp: String = "00:00"
)
