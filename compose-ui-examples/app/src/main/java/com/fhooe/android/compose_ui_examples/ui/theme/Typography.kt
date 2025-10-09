package com.fhooe.android.compose_ui_examples.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.fhooe.android.compose_ui_examples.R

object TextStyles {
    private val poppinsFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )

    val bodyOne = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF212121),
    )

    val bodyOneMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(500),
        color = Color(0xFF212121),
        textAlign = TextAlign.Center,
    )

    val bodyTwoMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF212121)
    )

    val bodyThreeRegular = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight(400),
        color = Color(0xFF9E9E9E),
    )
}