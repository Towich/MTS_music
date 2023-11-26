package com.example.mts_music.data

import kotlinx.serialization.Serializable

@Serializable
data class Meta_dataSerialization(
    val author_name:String,
    val music_name: String)
