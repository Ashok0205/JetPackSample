package com.example.jetpacksamplev1.ui.compos_view

import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.jetpacksamplev1.R


@Composable
fun Logo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = MaterialTheme.colors.isLight
) {
    val assetId = if (lightTheme) {
        R.drawable.ic_launch_round
    } else {
        R.drawable.ic_delish_logo
    }
    Image(
        painter = painterResource(id = assetId),
        modifier = modifier,
        contentDescription = null
    )
}