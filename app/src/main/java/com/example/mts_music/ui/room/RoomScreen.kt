package com.example.mts_music.ui.room

import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mts_music.R
import com.example.mts_music.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(
    navController: NavController,
    context: Context,
    mViewModel: RoomViewModel
) {
    val currentRoom = mViewModel.getCurrentRoom()
    val mp: MediaPlayer = mViewModel.getMediaPlayer()

    var isAudioPlaying: Boolean by remember { mutableStateOf(mp.isPlaying) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var uriInviteToRoom by remember { mutableStateOf("") }
    var uriQRCode: Bitmap? by remember { mutableStateOf(null) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = currentRoom.roomName) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.RoomsScreen.route) {
                            popUpTo(0)
                        }
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(it),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentRoom.playingAudio,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = currentRoom.authorRoom,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium
                    )


                    var musicTrackProgress by remember {
                        mutableFloatStateOf(0f)
                    }
                    Slider(
                        value = musicTrackProgress,
                        onValueChange = {
                            musicTrackProgress = it
                        },
                        onValueChangeFinished = {
                            mp.seekTo((musicTrackProgress * mp.duration.toFloat()).toInt())
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Мы хотим обновлять каждую секунду
                    // duration = 180 + 38 = 218

                    LaunchedEffect(key1 = musicTrackProgress, key2 = isAudioPlaying) {
                        if (isAudioPlaying) {
                            val deltaTime = 1000
                            delay(deltaTime.toLong())
                            musicTrackProgress += (deltaTime / mp.duration.toFloat())
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val currentSeconds = musicTrackProgress * mp.duration / 1000
                        val minutes = (currentSeconds / 60).toInt()
                        val seconds = (currentSeconds % 60).toInt()

                        val audioDurationSeconds = mp.duration / 1000
                        val minutesInAudio =
                            abs((currentSeconds - audioDurationSeconds) / 60).toInt()
                        val secondsUnAudio =
                            abs((currentSeconds - audioDurationSeconds) % 60).toInt()

                        Text(
                            text = String.format("%02d:%02d", minutes, seconds)
                        )
                        Text(
                            text = String.format("-%02d:%02d", minutesInAudio, secondsUnAudio)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if (!mp.isPlaying) {
                                    isAudioPlaying = true
                                    mp.start()
                                } else {
                                    isAudioPlaying = false
                                    mp.pause()
                                }

                                musicTrackProgress =
                                    (mp.currentPosition.toFloat() / mp.duration.toFloat())
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = if (isAudioPlaying) R.drawable.mp3_music_pause else R.drawable.mp3_music_play),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(82.dp)
                            )
                        }
                    }


                }
            }

            SecondaryButton(
                onClick = {
                    // TODO:Navigate to ChatScreen

                },
                text = "Перейти в Чат комнаты",
                backgroundColor = MaterialTheme.colorScheme.secondary,
                textColor = Color.Black,
                painter = painterResource(id = R.drawable.arrow_right),
                modifier = Modifier
                    .padding(top = 67.dp),
                iconModifier = Modifier
                    .size(35.dp)
            )

            SecondaryButton(
                onClick = {
                    scope.launch {
                        uriInviteToRoom = mViewModel.generateDeepLink(
                            roomId = currentRoom.id,
                            roomToken = currentRoom.roomToken
                        )
                        uriQRCode = mViewModel.generateQrCode(uriInviteToRoom)
                        sheetState.expand()
                    }.invokeOnCompletion {
                        if (sheetState.isVisible) {
                            showBottomSheet = true
                        }
                    }
                },
                text = "Добавить слушателя",
                backgroundColor = MaterialTheme.colorScheme.secondary,
                textColor = Color.Black,
                painter = painterResource(id = R.drawable.add_person),
                modifier = Modifier
                    .padding(top = 30.dp),
                iconModifier = Modifier
                    .size(35.dp)
            )

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    },
                    sheetState = sheetState,
                    modifier = Modifier
                        .height(500.dp),
                    containerColor = Color.White
                ) {
                    val clipboardManager: androidx.compose.ui.platform.ClipboardManager = LocalClipboardManager.current


                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp)
                    ) {
                        Text(
                            text = "Добавить слушателя",
                            fontSize = 20.sp
                        )

                        Text(
                            text = "Ссылка комнаты",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(top = 15.dp)
                        )

                        OutlinedTextField(
                            value = uriInviteToRoom,
                            readOnly = true,
                            onValueChange = {
                            },
                            singleLine = true,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                            ),
                            trailingIcon = {
                                IconButton(onClick = {
                                    clipboardManager.setText(AnnotatedString(uriInviteToRoom))
                                    Toast.makeText(context, "Скопировано!", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.copy_link),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                        )

                        Text(
                            text = "QR-код комнаты",
                            modifier = Modifier
                                .padding(top = 15.dp)

                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                bitmap = uriQRCode!!.asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                                modifier = Modifier
                            )
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    painter: Painter,
    modifier: Modifier,
    iconModifier: Modifier
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = modifier

    ) {
        Row(
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = textColor,
            )
            Icon(
                painter = painter,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = iconModifier
            )
        }
    }
}