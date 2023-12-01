package com.example.mts_music.ui.room

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.example.mts_music.API.ApiService
import com.example.mts_music.data.RoomMusicPlayer
import com.example.mts_music.data.NewRoomSerialization
import com.example.mts_music.data.Room
import com.example.mts_music.data.RoomIdNameUser_CountSerialization
import com.example.mts_music.data.RoomUserIdAccessNameSerialization
import okio.ByteString
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class RoomRepository(context: Context) {
    private var currentRoom: Room? = null
    private var connectToExistRoom = false
    private var roomIdToConnect: String? = null
    private val roomMusicPlayer = RoomMusicPlayer(context)



    private val apiService by lazy {
        ApiService.create()
    }

    fun setCurrentRoom(newRoom: Room) {
        currentRoom = newRoom
    }

    fun getCurrentRoom(): Room? = currentRoom

    fun getMediaPlayer(): MediaPlayer = roomMusicPlayer.getMediaPlayer()

    fun setConnectToExistRoom(newValue: Boolean) {
        connectToExistRoom = newValue
    }

    fun getConnectToExistRoom(): Boolean = connectToExistRoom


    fun setRoomIdToConnect(newValue: String) {
        roomIdToConnect = newValue
    }

    fun getRoomIdToConnect(): String = roomIdToConnect ?: "null"

    fun isTokenValid(token: String): Boolean {
        // TODO: Проверка токена на валидность
        return true
    }

    suspend fun getListOfRooms(): List<RoomIdNameUser_CountSerialization> {
        return apiService.getListOfRooms()
    }

    suspend fun postNewRoom(user_id: Int, nameOfRoom:String, access: Int, token: String): NewRoomSerialization {
        return apiService.postNewRoom(RoomUserIdAccessNameSerialization(user_id, nameOfRoom, access, token))
    }

    suspend fun getMusic(room_id: String, user_id: String) {
        apiService.getMusic(room_id, user_id)
    }

    suspend fun deleteRoom(roomId: String, userId: String?) {
        apiService.deleteRoom(roomId, userId)
    }


    fun addNextPartMusic() = roomMusicPlayer.addNextPartMusic()

    fun playBufferMediaPlayer() = roomMusicPlayer.playBufferMediaPlayer()
}