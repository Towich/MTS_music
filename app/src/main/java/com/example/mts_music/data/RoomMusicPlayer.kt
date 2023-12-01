package com.example.mts_music.data

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.core.net.toUri
import com.example.mts_music.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.system.measureTimeMillis


class RoomMusicPlayer(private val context: Context) {

    // create temp file that will hold byte array
    private val tempMp3 = File.createTempFile("tempMusic", ".mp3", context.externalCacheDir)
    private var mp: MediaPlayer


    private val bufferMp1: MediaPlayer
    private val bufferMp2: MediaPlayer
    private var isBufferMp1Playing: Boolean = true

    init {
        val inputStreamMusic = context.resources.openRawResource(R.raw.zveri_1)
        tempMp3.writeBytes(inputStreamMusic.readBytes())
        tempMp3.deleteOnExit()


        bufferMp1 = MediaPlayer.create(context, tempMp3.absoluteFile.toUri())
        bufferMp2 = MediaPlayer.create(context, tempMp3.absoluteFile.toUri())
        mp = bufferMp1
    }

    fun getMediaPlayer(): MediaPlayer = mp

    private fun setMusicFromByteArray(bytesFilePath: Array<String>): Array<String> {

        val resultConvertedFilePath = Array(bytesFilePath.size) { "" }

        try {

            for (i in bytesFilePath.indices) {
                val bytesFile = File(bytesFilePath[i])
                val resultMusicFile =
                    File("${bytesFilePath[i].substringBefore("_")}_converted_${i + 1}.mp3")
                if (!resultMusicFile.exists())
                    resultMusicFile.createNewFile()

                val fos = FileOutputStream(resultMusicFile)
                fos.write(bytesFile.readBytes())
                fos.close()

                resultConvertedFilePath[i] = resultMusicFile.path
            }


            return resultConvertedFilePath

        } catch (ex: IOException) {
            val s = ex.toString()
            ex.printStackTrace()
        }

        return emptyArray()
    }

    fun playSound(resid: Int) {

    }

    fun addNextPartMusic() {
        val inputStreamMusic2 = context.resources.openRawResource(R.raw.zveri_2)

        Log.i("tempMp3", "size = ${tempMp3.readBytes().size}")
        tempMp3.appendBytes(inputStreamMusic2.readBytes())
        Log.i("tempMp3", "size = ${tempMp3.readBytes().size}")
        Log.i("tempMp3", "exist?: ${tempMp3.exists()} - ${tempMp3.path}")


        try {
            if (isBufferMp1Playing) {
                bufferMp2.reset()
                bufferMp2.setDataSource(context, tempMp3.absoluteFile.toUri())
                bufferMp2.prepare()
            } else {
                bufferMp1.reset()
                bufferMp1.setDataSource(context, tempMp3.absoluteFile.toUri())
                bufferMp1.prepare()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


        Log.i("mp", "duration = ${mp.duration / 1000} seconds")
    }

    fun playBufferMediaPlayer() {

        mp.stop()

        mp = if (isBufferMp1Playing) {
            bufferMp2.start()
            bufferMp2.seekTo(mp.currentPosition)

            bufferMp2

        } else {
            bufferMp1.start()
            bufferMp1.seekTo(mp.currentPosition)

            bufferMp1
        }

        isBufferMp1Playing = !isBufferMp1Playing

        Log.i("bufferMp3", "isBufferMp1Playing = $isBufferMp1Playing")
    }
}