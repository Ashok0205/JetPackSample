package com.example.jetpacksamplev1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

lateinit var  uiColors: Colors

/*private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)*/
val LightThemeColors = lightColors(
    primary = Purple700,
    primaryVariant = Purple800,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Red800,
    onError = Color.White
)

val DarkThemeColors = darkColors(
    primary = Purple300,
    primaryVariant = Purple600,
    onPrimary = Color.Black,
    secondary = Color.Black,
    onSecondary = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    error = Red300,
    onError = Color.Black
)
/*private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    *//* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    *//*
)*/

@Composable
fun JetPackSampleV1Theme1(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

   val  colors = if (darkTheme) DarkThemeColors else LightThemeColors
    uiColors=colors
    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.onPrimary.copy(alpha = AlphaNearOpaque)
        )
    }



    MaterialTheme(
        colors =colors,
        typography =Typography,
        shapes = Shapes,
        content = content
    )
    /*--------------------*/


}

object Colors {
    val ThemeColors=uiColors
}




