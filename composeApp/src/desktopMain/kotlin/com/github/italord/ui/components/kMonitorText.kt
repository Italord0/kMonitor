package com.github.italord.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kmonitor.composeapp.generated.resources.Res
import kmonitor.composeapp.generated.resources.xolonium
import org.jetbrains.compose.resources.Font

@Composable
fun kMonitorText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    fontSize: TextUnit = 28.sp,
    textAlign: TextAlign = TextAlign.Left,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        textAlign = textAlign,
        color = textColor,
        fontFamily = FontFamily(Font(Res.font.xolonium))
    )
}