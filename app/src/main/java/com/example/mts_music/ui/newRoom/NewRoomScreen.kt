package com.example.mts_music.ui.newRoom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mts_music.data.Room
import com.example.mts_music.navigation.Screen
import com.example.mts_music.ui.auth.PrimaryButton
import java.util.UUID

@Composable
fun NewRoomScreen(
    navController: NavController,
    mViewModel: NewRoomViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        var newRoomText by remember { mutableStateOf("") }
        var isRoomPrivate by remember { mutableStateOf(false) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Создайте свою\nкомнату!",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Название комнаты",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(top = 45.dp)
                )
            }

            OutlinedTextField(
                value = newRoomText,
                onValueChange = { newText ->
                    newRoomText = newText
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                ),
                label = {
                    Text(text = "Введите название вашей комнаты...")
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Приватная комната",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier,
                )
                Checkbox(
                    checked = isRoomPrivate,
                    onCheckedChange = {
                        isRoomPrivate = it
                    }
                )
            }
        }

        Column() {
            PrimaryButton(
                onClick = {
                    val newRoom = Room(
                        id = UUID.randomUUID().toString(),
                        roomName = newRoomText,
                        access = if (isRoomPrivate) 0 else 1,
                        roomToken = UUID.randomUUID().toString(),
                    )

                    // TODO:SEND TO NIKITOS & MICHAIL

                    mViewModel.setCurrentRoom(newRoom = newRoom)
                    navController.navigate(Screen.RoomScreen.route){
                        popUpTo(0)
                    }
                },
                text = "Создать",
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = Color.White,
                modifier = Modifier
                    .padding(top = 45.dp)
            )

            PrimaryButton(
                onClick = {
                    navController.navigate(Screen.RoomsScreen.route) {
                        popUpTo(0)
                    }
                },
                text = "Отменить",
                backgroundColor = MaterialTheme.colorScheme.onSecondary,
                textColor = Color.White,
                modifier = Modifier
                    .padding(top = 23.dp)
            )
        }


    }
}