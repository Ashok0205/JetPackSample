package com.example.jetpacksamplev1.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import com.example.jetpacksamplev1.R

private const val SplashWaitTime: Long = 2000

@Composable
fun SplashView(modifier: Modifier = Modifier,onLauncherComplete: (LaunchDestination) -> Unit) {
    val viewModel: LauncherViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()
    Box(
        modifier = modifier.fillMaxSize()
            .background(Color.LightGray),//MaterialTheme.colors.background
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onLauncherComplete)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout(viewState.launchDestination)
        }

        Text(text = "Hello Test!", color = Color.Yellow)
        Card(
            modifier = Modifier.size(208.dp),
            shape = CircleShape,
            elevation = 2.dp
        ) {
            Image(
                painterResource(R.drawable.jetpack_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackSampleV1Theme {
       */
/* Image(
            painter = painterResource(id =  R.drawable.ic_delish_logo),
            contentDescription = null
        )*//*

        Text(text = "Hello Test!")
    }
}*/
