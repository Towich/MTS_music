package com.example.mts_music.data

data class Room(
    val roomName: String,
    val author: String,
    val users: List<User>,
    val access: Int
)
