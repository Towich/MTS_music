package com.example.mts_music.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mts_music.R
import com.example.mts_music.data.ChatMessage
import com.example.mts_music.data.Room
import com.example.mts_music.navigation.NavigationRouter
import com.example.mts_music.navigation.Screen
import com.example.mts_music.ui.rooms.RoomCard
import com.example.mts_music.ui.theme.Gray_66
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    mViewModel: ChatViewModel
) {
    val listOfMessages by mViewModel.listOfMessages.collectAsState()
    var newMessageText by remember { mutableStateOf("") }
    var buttonSendIsRed by remember { mutableStateOf(false) }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = listOfMessages.size-1
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Чат комнаты") },
                navigationIcon = {
                    IconButton(onClick = {
                        NavigationRouter.currentScreen.value = Screen.RoomScreen
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxHeight(fraction = 0.8f),
                reverseLayout = false,
                state = listState
            ) {
                items(listOfMessages) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = if (item.isMine) Arrangement.End else Arrangement.Start
                    ) {
                        MessageCard(chatMessage = item)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = newMessageText,
                    onValueChange = { newText ->
                        newMessageText = newText

                        buttonSendIsRed = newText.isNotEmpty()
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                    )
                )

                IconButton(
                    onClick = {
                        // TODO: Send message to NIKITOS & MICHAIL

                        val sdf = SimpleDateFormat("hh:mm")
                        val currentDate = sdf.format(Date())

                        mViewModel.addNewMessage(
                            ChatMessage(
                                id = UUID.randomUUID().toString(),
                                message = newMessageText,
                                author = "Вы", //TODO
                                isMine = true,
                                timestamp = currentDate
                                )
                        )

                        coroutineScope.launch {
                            // Animate scroll to the last item
                            listState.animateScrollToItem(listOfMessages.size-1)
                        }

                        newMessageText = ""
                    },
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.up_arrow),
                        contentDescription = null,
                        tint = if (buttonSendIsRed) MaterialTheme.colorScheme.primary else Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }

        }

    }
}

@Composable
fun MessageCard(
    chatMessage: ChatMessage
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(
                text = chatMessage.message,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = chatMessage.author,
                    color = Gray_66
                )
                Text(
                    text = chatMessage.timestamp,
                    color = Gray_66
                )
            }

        }
    }
}