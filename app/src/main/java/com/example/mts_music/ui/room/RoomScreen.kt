package com.example.mts_music.ui.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun RoomScreen(
    navController: NavController,
    mViewModel: RoomViewModel = viewModel()
) {
    Scaffold(
        topBar = {
//            CenterAlignedTopAppBar(title = { Text(text = ) })
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
        ){
            Card(
                modifier = Modifier
                    .padding(it)
            ) {
                Column() {
                    Text(
                        text = "О Боже какой мужчина папапапап"
                    )
                }
            }
        }

    }
}