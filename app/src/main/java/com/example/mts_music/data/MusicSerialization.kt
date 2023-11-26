package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class MusicSerialization(
    val data: String,
    val meta_data: Meta_dataSerialization,
    val packet_type: Int,
    val is_next: Boolean
)
