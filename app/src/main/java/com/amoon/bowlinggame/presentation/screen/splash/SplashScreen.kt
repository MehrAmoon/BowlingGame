package com.amoon.bowlinggame.presentation.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amoon.bowlinggame.R
import com.amoon.bowlinggame.presentation.navigation.AppScreens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    // Animatable value for scaling animation
    val scale = remember {
        Animatable(initialValue = 0f)
    }

    // Animation and navigation effects
    LaunchedEffect(key1 = true, block = {
        // Scale animation with overshoot effect
        scale.animateTo(
            targetValue = .9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        // Delay before navigating to main screen
        delay(timeMillis = 2000L)
        navController.navigate(route = AppScreens.MainScreen.name)
    })

    // UI layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Animated surface with text and icon
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .size(400.dp)
                .scale(scale = scale.value),
            shape = CircleShape,
            color = Color.Gray,
            border = BorderStroke(width = 2.dp, color = Color.Gray)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier  = Modifier.padding(top = 20.dp),
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h5,
                    color = Color.LightGray
                )

                Image(
                    painterResource(R.drawable.launcher2),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier  = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        }
    }
}