package com.fhooe.android.compose_ui_examples

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyles {
    private val poppinsFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )


    val bodyOneRegular = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF212121),
    )

    val bodyTwoRegular = TextStyle(
        fontSize = 14.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF9E9E9E),
    )

    val headingTwoSemibold = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(600),
        color = Color(0xFF212121),

        )

    val bodyTwoMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF212121),
    )

    val bodyOneMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF212121),
    )

    val bodyThreeRegular = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF212121),
    )
}