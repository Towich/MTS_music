package com.example.mts_music.data

data class NewRoom (
    val id: String = "error",
    val name: String = "error",
    val author: UserSerialization,
    val users: List<UserSerialization>,
    val access: Int,
)