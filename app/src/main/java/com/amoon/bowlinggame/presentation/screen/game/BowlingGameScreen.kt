package com.amoon.bowlinggame.presentation.screen.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amoon.bowlinggame.R
import com.amoon.bowlinggame.presentation.navigation.AppScreens
import com.amoon.bowlinggame.presentation.ui.theme.BlossomOrange
import com.amoon.bowlinggame.presentation.viewModel.BowlingViewModel


@Composable
fun BowlingGameScreen(
    navController: NavController,
    viewModel: BowlingViewModel,
    userName: String
) {
    var pins by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3,
            color = BlossomOrange
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display the current score
        Text(
            "$userName's Score: ${viewModel.score.value}",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display all rolls
        Text(
            "${stringResource(R.string.rolls)} ${viewModel.rolls.value.joinToString(", ")}",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(46.dp))

        // Display game finished message
        if (viewModel.isGameFinished()) {
            Text(
                stringResource(R.string.game_finished),
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Button to see the scores
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.ScoreScreen.name)

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.all_scores))
            }

            // Button to restart the game
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.MainScreen.name)

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.restart))
            }

        } else {

            // Button to roll the ball
            Button(
                onClick = {
                    rollPin(viewModel, userName, pins)
                    pins = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.roll))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TextField to input the number of pins knocked down
            OutlinedTextField(
                value = pins,
                singleLine = true,
                onValueChange = { value -> pins = value },
                label = { Text(stringResource(R.string.pins_knocked_down)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        rollPin(viewModel, userName, pins)
                        pins = ""
                    }),
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.pins_hint)) }
            )

        }
    }
}

private fun rollPin(
    viewModel: BowlingViewModel,
    userName: String,
    pins: String
) {
    if (pins.isNotEmpty())
        viewModel.roll(userName, pins)
}
