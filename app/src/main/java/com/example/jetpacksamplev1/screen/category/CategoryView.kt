package com.example.jetpacksamplev1.screen.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme

@Composable
fun CategoryView(profileViewModel: CategoryViewModel, s: String)
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,

    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = JetPackSampleV1Theme.colors.textLink)
            , contentAlignment = Alignment.Center


        ) {
            HeaderTitle()
        }
    }
}

@Composable
fun HeaderTitle() {
    Text(
        text = stringResource(id = R.string.category),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
    )
}