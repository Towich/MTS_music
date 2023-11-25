package com.example.mts_music.ui.room

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mts_music.R
import com.example.mts_music.navigation.Screen
import kotlinx.coroutines.delay
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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = currentRoom.roomName) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.RoomsScreen.route){
                            popUpTo(0)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
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
                        style = MaterialTheme.typography.titleLarge
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
                    160

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
                        val minutesInAudio = abs((currentSeconds - audioDurationSeconds) / 60).toInt()
                        val secondsUnAudio = abs((currentSeconds - audioDurationSeconds) % 60).toInt()

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
        }

    }
}