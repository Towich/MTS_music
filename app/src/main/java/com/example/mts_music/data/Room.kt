package com.example.mts_music.data

data class Room(
    val author: String,
    val users: List<User>,
    val access: Int
)
