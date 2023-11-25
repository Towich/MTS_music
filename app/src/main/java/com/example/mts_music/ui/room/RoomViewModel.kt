package com.example.mts_music.ui.room

import android.content.Context
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.Room

class RoomViewModel(context: Context, private val repository: RoomRepository): ViewModel() {

    fun getCurrentRoom(): Room {
        return repository.getCurrentRoom() ?: Room()
    }

    private fun generateQrCode(text: String) {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 400)
        try {
            val bitMap = qrGenerator.bitmap
            //imageView.setImageBitmap(bitMap)

        }catch (e:Exception) {
            Log.v("exception with QR-code", e.toString())
        }
    }

    class RoomViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RoomViewModel(context, repository) as T
    }
}