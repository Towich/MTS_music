package com.example.mts_music.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mts_music.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val sheetStateSetNumber = rememberModalBottomSheetState(
       skipPartiallyExpanded = true
    )
    val sheetStateGetSms = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it != SheetValue.Hidden
        }
    )

    var showSetNumberBottomSheet by remember { mutableStateOf(false) }
    var showGetSmsBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 30.dp, top = 30.dp, end = 30.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.mts_logo),
            contentDescription = "MTS logo",
            modifier = Modifier
                .size(64.dp)
        )

        Text(
            text = stringResource(id = R.string.digital_economy),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 15.dp)
        )

        PrimaryButton(
            onClick = {
                showSetNumberBottomSheet = true
            },
            text = stringResource(id = R.string.auth_text),
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = 70.dp)
        )

//        PrimaryButton(
//            onClick = {
//                showBottomSheet = true
//            },
//            text = stringResource(id = R.string.register_text),
//            backgroundColor = MaterialTheme.colorScheme.secondary,
//            textColor = MaterialTheme.colorScheme.onSecondary,
//            modifier = Modifier
//                .padding(top = 14.dp)
//        )


        if (showSetNumberBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showSetNumberBottomSheet = false
                },
                sheetState = sheetStateSetNumber,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                var showIncorrectPhone by remember {
                    mutableStateOf(false)
                }

                var numberText by remember {
                    mutableStateOf("+79")
                }

                Column(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mts_logo),
                            contentDescription = "MTS logo",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Text(
                            text = "ID",
                            fontSize = 32.sp,
                            modifier = Modifier
                                .padding(start = 7.dp)
                        )
                    }

                    Text(
                        text = "Введите номер телефона\nлюбого оператора",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )

                    OutlinedTextField(
                        value = numberText,
                        onValueChange = { newText ->
                            numberText = newText
                            showIncorrectPhone = false
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    )

                    if(showIncorrectPhone){
                        Text(
                            text = "Некорректный номер",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    PrimaryButton(
                        onClick = {
                            if(viewModel.isPhoneCorrect(numberText)){
                                showSetNumberBottomSheet = false
                                showGetSmsBottomSheet = true
                            }
                            else{
                                showIncorrectPhone = true
                            }
                        },
                        text = stringResource(id = R.string.next),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(top = 30.dp)
                    )
                }
            }
        }
        else if(showGetSmsBottomSheet){
            ModalBottomSheet(
                onDismissRequest = {

                },
                sheetState = sheetStateGetSms,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mts_logo),
                            contentDescription = "MTS logo",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Text(
                            text = "ID",
                            fontSize = 32.sp,
                            modifier = Modifier
                                .padding(start = 7.dp)
                        )
                    }

                    Text(
                        text = "Введите код из SMS",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )

                    Text(
                        text = "Мы отправили его на\n",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )


                    var smsText by remember { mutableStateOf("") }
                    DuckieTextField(
                        text = smsText,
                        onTextChanged = {
                            if(viewModel.isDigits(it) && it.length <= 5){
                                smsText = it
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DuckieTextField(
    text: String,
    onTextChanged: (String) -> Unit,
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                text.forEachIndexed { index, char ->
                    DuckieTextFieldCharContainer(
                        text = char,
                        isFocused = index == text.lastIndex,
                    )
                }
                repeat(5 - text.length) {
                    DuckieTextFieldCharContainer(
                        text = ' ',
                        isFocused = false,
                    )
                }
            }
        },
    )
}

@Composable
private fun DuckieTextFieldCharContainer(
    modifier: Modifier = Modifier,
    text: Char,
    isFocused: Boolean,
) {
    val shape = remember { RoundedCornerShape(4.dp) }

    Box(
        modifier = modifier
            .size(
                width = 58.dp,
                height = 80.dp,
            )
            .background(
                color = Color(0xFFF6F6F6),
                shape = shape,
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
            .run {
                if (isFocused) {
                    border(
                        width = 1.dp,
                        color = Color(0xFFFF8300),
                        shape = shape,
                    )
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
    }
}
