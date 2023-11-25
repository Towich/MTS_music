package com.example.mts_music.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mts_music.R
import com.example.mts_music.ui.auth.AuthRepository
import com.example.mts_music.ui.auth.PrimaryButton
import com.example.mts_music.ui.theme.Black_1E
import com.example.mts_music.ui.theme.Orange
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel
) {
    var usernameText by remember { mutableStateOf(viewModel.getUsername()) }
    var showUsernameErrorText by remember { mutableStateOf(false) }
    var hasChangesInUsername by remember { mutableStateOf(false) }
    var showExitFromAccountBottomSheet by remember { mutableStateOf(false) }

    val sheetStateExitFromAccount = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it != SheetValue.Expanded
        }
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 30.dp, top = 30.dp, end = 30.dp)
    ) {
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.displayMedium
        )

        Text(
            text = "Как к вам обращаться?",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(top = 45.dp)
        )

        OutlinedTextField(
            value = usernameText,
            onValueChange = { newText ->
                usernameText = newText
                showUsernameErrorText = usernameText.isEmpty()
                hasChangesInUsername = true
            },
            singleLine = true,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary
            )
        )

        if (showUsernameErrorText) {
            Text(
                text = "Поле не может быть пустым!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 5.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 35.dp)
        ) {
            Text(
                text = "Телефон",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary,
            )

            Icon(
                painter = painterResource(id = R.drawable.lock_icon),
                contentDescription = "lock icon",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(20.dp)
            )
        }


        OutlinedTextField(
            value = "+7 902 043 26 09",
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
            )
        )

        if (hasChangesInUsername) {
            PrimaryButton(
                onClick = {
                    viewModel.setUsername(usernameText)
                },
                text = "Сохранить",
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(top = 40.dp)
            )
        }

        PrimaryButton(
            onClick = {
                showExitFromAccountBottomSheet = true
            },
            text = "Выйти",
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .padding(top = 40.dp)
        )

        if (showExitFromAccountBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    scope.launch { sheetStateExitFromAccount.hide() }.invokeOnCompletion {
                        if(!sheetStateExitFromAccount.isVisible){
                            showExitFromAccountBottomSheet = false
                        }
                    }
                },
                sheetState = sheetStateExitFromAccount,
                modifier = Modifier
                    .height(400.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Точно выйти?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Вы не сможете заходить в комнаты и синхронно прослушивать музыку с другими участниками",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 15.dp),
                    )

                    PrimaryButton(
                        onClick = {
                            scope.launch { sheetStateExitFromAccount.hide() }.invokeOnCompletion {
                                if(!sheetStateExitFromAccount.isVisible){
                                    showExitFromAccountBottomSheet = false
                                }
                            }
                        },
                        text = "Остаться",
                        backgroundColor = Black_1E,
                        textColor = Color.White,
                        modifier = Modifier
                            .padding(top = 30.dp)
                    )

                    PrimaryButton(
                        onClick = { /*TODO*/ },
                        text = "Выйти",
                        backgroundColor = MaterialTheme.colorScheme.secondary,
                        textColor = Orange,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )
                }
            }
        }
    }
}