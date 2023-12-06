package com.amoon.bowlinggame.presentation.screen.scores

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoon.bowlinggame.R
import com.amoon.bowlinggame.presentation.screen.components.ScoreTableRow
import com.amoon.bowlinggame.presentation.viewModel.ScoresViewModel

@Composable
fun UserScoresScreen(viewModel: ScoresViewModel) {
    val userScores by viewModel.userScores.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp , end = 16.dp , top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( stringResource(R.string.all_scores),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(userScores) { index, userScore ->
                ScoreTableRow(userName = userScore.user_name, score = userScore.score)
                if (index < userScores.size ) {
                    // Add a Divider for each row
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}
