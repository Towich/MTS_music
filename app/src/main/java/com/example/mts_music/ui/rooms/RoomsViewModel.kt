package com.example.mts_music.ui.rooms

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.Converter
import com.example.mts_music.data.Room
import com.example.mts_music.ui.room.RoomRepository

class RoomsViewModel(context: Context, private val repository: RoomRepository): ViewModel() {
    val converter = Converter()
    var rooms: List<Room> = listOf()

    fun setCurrentRoom(newRoom: Room) = repository.setCurrentRoom(newRoom)

    fun getConnectToExistRoom(): Boolean = repository.getConnectToExistRoom()
    fun getRoomIdToConnect(): String = repository.getRoomIdToConnect()

    suspend fun getListOfRooms() {
        val listOfRooms = repository.getListOfRooms()
        val mutableListofRooms = mutableListOf<Room>()
        for(room in listOfRooms) {
            mutableListofRooms.add(Room(room.id.toString(), room.name, "error", "error",
                "error", "error", listOf(), 1))
        }
        rooms = mutableListofRooms.toList()
    }

    class RoomsViewModelFactory(private val context: Context, private val repository: RoomRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RoomsViewModel(context, repository) as T
    }

}