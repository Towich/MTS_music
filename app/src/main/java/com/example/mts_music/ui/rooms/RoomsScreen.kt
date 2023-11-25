package com.example.mts_music.ui.rooms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mts_music.R
import com.example.mts_music.data.Room

@Composable
fun RoomsScreen(
    navController: NavController,
    mViewModel: RoomsViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 30.dp, end = 20.dp)
    ) {
        Text(
            text = "Комнаты",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        )

        LazyVerticalGrid(
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 30.dp)
        ) {
            itemsIndexed(
                items = mViewModel.rooms,
                key = { _: Int, item: Room ->
                    item.hashCode()
                }
            ) { _, item ->
                RoomCard(room = item)
            }
        }
    }
}

@Composable
fun RoomCard(
    room: Room
) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 15.dp)
            .height(150.dp)
            .clickable { /* TODO */ },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = room.roomName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${room.numberOfUsers}/5",
                    style = MaterialTheme.typography.titleLarge,
                )
                Icon(
                    painter = painterResource(
                        id = R.drawable.audio_icon
                    ),
                    contentDescription = "audio icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}