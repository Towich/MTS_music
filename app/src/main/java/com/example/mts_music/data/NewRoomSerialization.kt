package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class NewRoomSerialization (
    val id: String = "error",
    val name: String = "error",
    val author: UserSerialization,
    val users: List<UserSerialization>,
    val access: Int,
)