package com.example.composeautomation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composeautomation.R

// Set of Material typography styles to start with

val  koodak = FontFamily(
    Font(R.font.koodak)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h5 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp
    ),
    h3 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp
    ),
    h1 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = koodak,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)