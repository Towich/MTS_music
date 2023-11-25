package com.example.mts_music.data

data class Room(
    val id: String,
    val roomName: String,
    val author: String,
    val users: List<User>,
    val access: Int
)
