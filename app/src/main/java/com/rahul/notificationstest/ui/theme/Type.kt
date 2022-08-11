package com.rahul.notificationstest.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.notificationstest.R

private val fontFamilyKulim = FontFamily(
    listOf(
        Font(
            resId = R.font.kulim_park_regular
        ),
        Font(
            resId = R.font.kulim_park_light,
            weight = FontWeight.Light
        )
    )
)

private val fontFamilyLato = FontFamily(
    listOf(
        Font(
            resId = R.font.lato_regular
        ),
        Font(
            resId = R.font.lato_bold,
            weight = FontWeight.Bold
        )
    )
)

private val fontFamilyCircularStd = FontFamily(
    listOf(
        Font(
            resId = R.font.circular_std_bold,
            weight = FontWeight.Bold
        ),
        Font(
            resId = R.font.circular_std_book,
            weight = FontWeight.Normal
        )
    )
)

val typography = Typography(
    defaultFontFamily = fontFamilyCircularStd,
    h1 = TextStyle(
        fontFamily = fontFamilyCircularStd,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (1.15).sp
    ),
    h2 = TextStyle(
        fontFamily = fontFamilyCircularStd,
        fontSize = 15.sp,
        letterSpacing = (1.15).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (1.15).sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = (1.15).sp
    ),
)
