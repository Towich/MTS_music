package com.example.mts_music.ui.chat

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.data.ChatMessage
import com.example.mts_music.ui.auth.AuthRepository
import com.example.mts_music.ui.auth.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class ChatViewModel(context: Context, private val repository: ChatRepository) : ViewModel() {

    var listOfMessages = MutableStateFlow(
        listOf(
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Привет!",
                author = "Антон",
                isMine = true
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Я балбес",
                author = "Антон",
                isMine = true
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Ты сегодня запушил все коммиты на master?",
                author = "Антон",
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Я всё это время был на мастере...",
                author = "Никитос",
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Бегите, я сумашедший."
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Kawasaki.",
                isMine = true
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Cago."
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Criko"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "i Esstriper"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "КАКАЯ ОБАЛДЕННАЯ ПЕСНЯ!!!!!"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "ДОБАВЛЮ ЕЁ НА ЗВОНОК!!!!"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "ага"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "лол"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Никитос - лучший"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Да, согласен"
            ),
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = "Пам-пам-парам"
            )
        )
    )

    fun addNewMessage(chatMessage: ChatMessage) {
        listOfMessages.value = listOfMessages.value.plus(chatMessage)
    }

    class ChatViewModelFactory(
        private val context: Context,
        private val repository: ChatRepository
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ChatViewModel(context, repository) as T
    }
}