package com.example.mts_music.ui.rooms

import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.lifecycle.ViewModel
import com.example.mts_music.data.Room
import com.example.mts_music.data.User

class RoomsViewModel: ViewModel() {
    val rooms: List<Room> = listOf(
        Room(
            id = "0001",
            roomName = "Комната №1",
            author = "Мария",
            users = listOf(User("+71234567890", "Мария"), User("+75678904637", "Никита"), User("+75647890329", "Иван")),
            access = 1
        ),
        Room(
            id = "0002",
            roomName = "Комната МусиМисиМуси МУси МИссси Муси смсимисми",
            author = "Никита",
            users = listOf(User("+75678904637", "Никита"), User("+75647890329", "Михаил")),
            access = 1
        ),
        Room(
            id = "0003",
            roomName = "Комната Никиты",
            author = "Никита",
            users = listOf(User("+75678904637", "Никита")),
            access = 1
        ),
        Room(
            id = "0004",
            roomName = "Комната Муси",
            author = "Иван",
            users = listOf(User("+75678904637", "Иван"), User("+75647890329", "Мария")),
            access = 1
        ),
        Room(
            id = "0005",
            roomName = "Комната №254",
            author = "Михаил",
            users = listOf(User("+71234567890", "Мария"), User("+75678904637", "Никита"), User("+75647890329", "Иван")),
            access = 1
        ),
    )

    private fun generateQrCode(text: String) {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 400)
        try {
            val bitMap = qrGenerator.bitmap
            //imageView.setImageBitmap(bitMap)

        }catch (e:Exception) {
            Log.v("exception with QR-code", e.toString())
        }
    }
}