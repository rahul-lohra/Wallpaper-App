package com.core.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

internal val LightColorPalette = lightColors(
    primary = Color.Black,
    secondary = rust600,
    background = Color.White,
    surface = greySearch,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = grey5
)

internal val DarkColorPalette = darkColors(
    primary = Color.Black,
    secondary = rust300,
    background = gray900,
    surface = Color.White.copy(alpha = 0.15f),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = taupe100,
    onSurface = Color.White.copy(alpha = .8f)
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
