package com.amoon.bowlinggame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amoon.bowlinggame.presentation.screen.game.BowlingGameScreen
import com.amoon.bowlinggame.presentation.screen.main.MainScreen
import com.amoon.bowlinggame.presentation.screen.scores.UserScoresScreen
import com.amoon.bowlinggame.presentation.screen.splash.SplashScreen
import com.amoon.bowlinggame.presentation.viewModel.BowlingViewModel
import com.amoon.bowlinggame.presentation.viewModel.ScoresViewModel

@Composable
fun AppNavigation() {

    // Create a navigation controller
    val navController = rememberNavController()

    // Define navigation routes
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name) {
        // SplashScreen route
        composable(route = AppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = AppScreens.MainScreen.name) {
            // Display the MainScreen with the retrieved data
            MainScreen(
                navController = navController
            )
        }

        // BowlingGameScreen route
        val bowlingScreenRoute = AppScreens.BowlingGameScreen.name
        composable(
            route = "$bowlingScreenRoute/{userName}",
            arguments = listOf(
                navArgument(name = "userName", builder = {
                    type = NavType.StringType
                })
            )
        ) {navBackStackEntry ->
            // Retrieve the userName argument from the navigation entry
            val userName = navBackStackEntry.arguments?.getString("userName")!!

            // Obtain bowlingViewModel using Hilt
            val bowlingViewModel = hiltViewModel<BowlingViewModel>()

            BowlingGameScreen(navController = navController,
                viewModel = bowlingViewModel,
                userName)
        }

        // ScoreScreen route
        composable(route = AppScreens.ScoreScreen.name) {
            // Obtain ScoresViewModel using Hilt
            val scoresViewModel = hiltViewModel<ScoresViewModel>()

            // Display the UserScoresScreen
            UserScoresScreen(viewModel = scoresViewModel)
        }
    }
}
