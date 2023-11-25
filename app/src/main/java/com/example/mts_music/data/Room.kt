package com.example.mts_music.data

data class Room(
    val id: String = "error",
    val roomName: String = "error",
    val playingAudio: String = "error",
    val authorAudio: String = "error",
    val authorRoom: String = "error",
    val users: List<User> = listOf(),
    val access: Int = 0,

    )
