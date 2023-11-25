package com.example.mts_music.ui.room

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.Room

class RoomViewModel(context: Context, private val repository: RoomRepository): ViewModel() {

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

    class RoomViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RoomViewModel(context, repository) as T
    }
}