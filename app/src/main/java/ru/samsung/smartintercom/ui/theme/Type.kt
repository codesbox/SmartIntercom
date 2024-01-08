package ru.samsung.smartintercom.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val Typography.button get() = Button

object Button {
    val large = base.copy(
        fontSize = 32.sp,
        lineHeight = 36.sp,
    )

    val normal = base.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    )

    private val base get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
    )
}
