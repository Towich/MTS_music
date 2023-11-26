package com.example.mts_music.ui.room

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.Constants.ID
import com.example.mts_music.SharedPreferences
import com.example.mts_music.WebSocketListener
import com.example.mts_music.data.Room
import okhttp3.OkHttpClient
import okhttp3.WebSocket

class RoomViewModel(context: Context, private val repository: RoomRepository) : ViewModel() {
    val sharedPreference: SharedPreferences = SharedPreferences(context = context)

    fun getCurrentRoom(): Room {
        return repository.getCurrentRoom() ?: Room()
    }

    fun generateQrCode(text: String): Bitmap? {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 600)
        try {
            return qrGenerator.getBitmap(0)

        } catch (e: Exception) {
            Log.v("exception with QR-code", e.toString())
        }
        return null
    }

    fun getMediaPlayer(): MediaPlayer = repository.getMediaPlayer()

    fun generateDeepLink(roomId: String, roomToken: String): String {
        val currentRoom = getCurrentRoom()
        if (currentRoom.id.isNotEmpty() && currentRoom.roomToken.isNotEmpty()) {
            return "https://mtsroom.com/room_screen?id=$roomId&token=$roomToken"
        }
        return ""
    }

    fun getConnectToExistRoom(): Boolean = repository.getConnectToExistRoom()
    fun getRoomIdToConnect(): String = repository.getRoomIdToConnect()

    suspend fun getMoreMusic() {
        Log.d("getCurrentRoom().id", getCurrentRoom().id)
        Log.d("getCurrentRoom().name", getCurrentRoom().roomName)
        repository.getMusic(getCurrentRoom().id, sharedPreference.getValueInt(ID).toString())
    }

    fun makeConnectByWebSocket() = viewModelScope.launch {
        val request: okhttp3.Request = okhttp3.Request.Builder()
            .url("https://xaxatonmtc.onrender.com/connect/$user_id")
            .build()

        val client = OkHttpClient()
        val listener = WebSocketListener() {bytes: ByteString ->
            repository.setMusicFromByteArray(bytes)
        }
        val ws: WebSocket = client.newWebSocket(request, listener)
        repository.getMusic(getCurrentRoom().id, user_id.toString())


    }

    suspend fun deleteRoom(id: String) {
        repository.deleteRoom(id, sharedPreference.getValueInt(ID).toString())
    }


    class RoomViewModelFactory(
        private val context: Context,
        private val repository: RoomRepository
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            RoomViewModel(context, repository) as T
    }
}