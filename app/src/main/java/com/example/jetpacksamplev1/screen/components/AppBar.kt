package com.example.jetpacksamplev1.screen.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacksamplev1.R


@Composable
fun AppBar(
    title: @Composable () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.primary,
    actions: @Composable RowScope.() -> Unit = {},
    elevation: Dp = AppBarDefaults.TopAppBarElevation
)
{
    TopAppBar(
        title = title,
        actions = actions,
        elevation = elevation,
        backgroundColor=backgroundColor,
    )
}