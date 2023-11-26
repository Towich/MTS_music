package com.example.mts_music

import android.content.Context
import android.util.Log
import io.ktor.http.ContentType.Application.Json
import kotlinx.serialization.json.Json
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class WebSocketListener(val onMusicMessage: (bytes: ByteString) -> Unit): WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d("WebSocket", "connected")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        Log.e("message websocket", bytes.toString())
        onMusicMessage(bytes)
    }


    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        webSocket.close(1000, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response);
        t.message?.let { Log.d("WebSocket Error", it) }
    }

    fun outPut(text:String) {
        Log.d("WebSocket", text)
    }

}