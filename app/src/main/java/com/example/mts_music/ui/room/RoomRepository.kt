package com.example.mts_music.ui.room

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.example.mts_music.API.ApiService
import com.example.mts_music.R
import com.example.mts_music.data.NewRoomSerialization
import com.example.mts_music.data.Room
import com.example.mts_music.data.RoomIdNameUser_CountSerialization
import com.example.mts_music.data.RoomUserIdAccessNameSerialization
import okio.ByteString
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class RoomRepository(private val context: Context) {
    private var currentRoom: Room? = null
    private var connectToExistRoom = false
    private var roomIdToConnect: String? = null
    private val mp: MediaPlayer = MediaPlayer.create(context, R.raw.zveri)

    // create temp file that will hold byte array
    val tempMp3 = File.createTempFile("kurchina", "mp3", context.cacheDir)

    init {
        tempMp3.deleteOnExit()
    }

    private val apiService by lazy {
        ApiService.create()
    }

    fun setCurrentRoom(newRoom: Room) {
        currentRoom = newRoom
    }

    fun getCurrentRoom(): Room? = currentRoom

    fun getMediaPlayer(): MediaPlayer = mp

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

    fun setMusicFromByteArray(byteString: ByteString) {

        Log.i("readBytes()", byteString.size.toString())

        try {
            val fos = FileOutputStream(tempMp3)
            fos.write(byteString.toByteArray())
            fos.close()

            // resetting mediaplayer instance to evade problems
            mp.reset()

            // In case you run into issues with threading consider new instance like:
            // MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            val fis = FileInputStream(tempMp3)
            mp.setDataSource(fis.fd)
            mp.prepare()
            mp.start()
        } catch (ex: IOException) {
            val s = ex.toString()
            ex.printStackTrace()
        }

    }

    fun playSound(resid: Int) {
        val eSound = MediaPlayer.create(context, resid)
        eSound.start()
    }

    suspend fun deleteRoom(roomId: String, userId: String?) {
        apiService.deleteRoom(roomId, userId)
    }
}