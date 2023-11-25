package com.example.mts_music.ui.rooms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun RoomsScreen(
    navController: NavController,
    mViewModel: RoomsViewModel = viewModel()
){
    Column(
        modifier = Modifier
            .padding(start = 30.dp, top = 30.dp, end = 30.dp)
    ) {
        Text(
            text = "Комнаты",
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
fun RoomCard(

){

}