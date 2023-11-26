package com.example.mts_music.ui.rooms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mts_music.R
import com.example.mts_music.data.Room
import com.example.mts_music.navigation.NavigationRouter
import com.example.mts_music.navigation.Screen
import com.example.mts_music.ui.auth.PrimaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomsScreen(
    navController: NavController,
    mViewModel: RoomsViewModel
) {

    var showBottomSheet by remember { mutableStateOf(mViewModel.getConnectToExistRoom()) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 30.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Комнаты",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            )
            IconButton(onClick = {
                NavigationRouter.currentScreen.value = Screen.NewRoomScreen
                navController.navigate(Screen.NewRoomScreen.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }


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
                RoomCard(
                    room = item,
                    onClick = {
                        mViewModel.setCurrentRoom(item)
                        NavigationRouter.currentScreen.value = Screen.RoomScreen
                        navController.navigate(Screen.RoomScreen.route)
                    }
                )
            }
        }

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
                    .height(400.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Желаете войти в комнату #${mViewModel.getRoomIdToConnect()}?",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                    PrimaryButton(
                        onClick = {
                            // TODO: Отправляем Никитосу и Мише запрос на подключение
                            NavigationRouter.currentScreen.value = Screen.RoomScreen
                            navController.navigate(Screen.RoomScreen.route)
                        },
                        text = "Войти",
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        textColor = Color.White,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )
                    PrimaryButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        text = "Нет",
                        backgroundColor = MaterialTheme.colorScheme.onSecondary,
                        textColor = Color.White,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RoomCard(
    room: Room,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 15.dp)
            .height(150.dp)
            .clickable { onClick() },
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
                    text = "${room.users.size}/5",
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