package fhnw.emoba.blockbuster.model

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.luminance
import kotlin.math.max
import kotlin.math.min


data class Palette(
    val bgColor: Color,
    val fgColor: Color,
    val accentColor: Color,
)

fun getPalette(image: ImageBitmap): Palette {
    val palette = androidx.palette.graphics.Palette.from(image.asAndroidBitmap()).generate()
    val bgColor = Color(palette.dominantSwatch?.rgb ?: 0)
    val fgColor = Color(palette.dominantSwatch?.titleTextColor ?: 0)

    val vibrantColor = Color(palette.vibrantSwatch?.rgb ?: 0)
    val lightVibrantColor = Color(palette.lightVibrantSwatch?.rgb ?: 0)
    val darkVibrantColor = Color(palette.darkVibrantSwatch?.rgb ?: 0)
    val mutedColor = Color(palette.mutedSwatch?.rgb ?: 0)
    val darkMutedColor = Color(palette.darkMutedSwatch?.rgb ?: 0)
    val lightMutedColor = Color(palette.lightMutedSwatch?.rgb ?: 0)

    val accentColors = listOf(
        vibrantColor,
        mutedColor,
        darkMutedColor,
        lightVibrantColor,
        darkVibrantColor,
        lightMutedColor
    )
    val bestAccentColor = accentColors.maxByOrNull {
        contrastRatio(
            it,
            bgColor
        )
    } ?: fgColor

    val accentColor =
        if (contrastRatio(bgColor, bestAccentColor) < 14) bestAccentColor else darkMutedColor

    return Palette(bgColor, fgColor, accentColor)
}

fun contrastRatio(color1: Color, color2: Color): Double {
    val lum1 = color1.luminance()
    val lum2 = color2.luminance()
    return (max(lum1, lum2) + 0.05) / (min(lum1, lum2) + 0.05)
}