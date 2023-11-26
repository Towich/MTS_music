package com.example.mts_music.ui.newRoom

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.Constants.ID
import com.example.mts_music.SharedPreferences
import com.example.mts_music.data.Converter
import com.example.mts_music.data.Room
import com.example.mts_music.data.User
import com.example.mts_music.ui.room.RoomRepository

class NewRoomViewModel(context: Context, private val repository: RoomRepository) : ViewModel() {

    val converter = Converter()
    val sharedPreference: SharedPreferences = SharedPreferences(context = context)
    fun setCurrentRoom(newRoom: Room) = repository.setCurrentRoom(newRoom)
    fun getCurrentRoom(): Room {
        return repository.getCurrentRoom() ?: Room()
    }

    suspend fun postNewRoom(newRoom: Room): Room {
        val roomSerialization = repository.postNewRoom(sharedPreference.getValueInt(ID)!!, newRoom.roomName,
            newRoom.access, newRoom.roomToken)
        val mutableListUser = mutableListOf<User>()
        val room = Room(roomSerialization.id, roomSerialization.name,
            newRoom.roomToken, "error", "error", converter.convertUserSerializationToUser(roomSerialization.author).username,
            listOf(), roomSerialization.access)

        return room
    }

    class NewRoomViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = NewRoomViewModel(context, repository) as T
    }
}