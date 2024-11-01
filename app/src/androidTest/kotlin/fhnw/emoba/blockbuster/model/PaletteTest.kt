package fhnw.emoba.blockbuster.model

import androidx.compose.ui.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaletteTest {

    @Test
    fun testContrastRatio_highContrast() {
        val black = Color(0xFF000000)
        val white = Color(0xFFFFFFFF)
        val contrast = contrastRatio(black, white)
        assertEquals(21.0, contrast, 0.01)
    }
}
