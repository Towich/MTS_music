package com.example.mts_music.data

data class Room(
    val roomName: String,
    val numberOfUsers: Int,
    val users: List<User>
)
