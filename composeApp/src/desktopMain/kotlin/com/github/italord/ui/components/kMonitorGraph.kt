package com.github.italord.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun kMonitorGraph(
    dataPoints: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color.Blue,
    strokeWidth: Float = 4f,
    labelColor: Color = Color.White,
    labelTextSize: Float = 12f
) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        drawLineGraph(dataPoints, lineColor, strokeWidth, labelColor, labelTextSize, textMeasurer)
    }
}

fun DrawScope.drawLineGraph(
    dataPoints: List<Float>,
    lineColor: Color,
    strokeWidth: Float,
    labelColor: Color,
    labelTextSize: Float,
    textMeasurer: TextMeasurer
) {
    if (dataPoints.isEmpty()) return

    val graphWidth = size.width
    val graphHeight = size.height
    val maxDataPoint = dataPoints.maxOrNull() ?: 1f
    val minDataPoint = dataPoints.minOrNull() ?: 0f
    val padding = 16.dp.toPx()

    val path = Path()
    dataPoints.forEachIndexed { index, dataPoint ->
        val x = padding + (index * (graphWidth - 2 * padding) / (dataPoints.size - 1).coerceAtLeast(1))
        val y =
            graphHeight - padding - (graphHeight - 2 * padding) * (dataPoint - minDataPoint) / (maxDataPoint - minDataPoint).coerceAtLeast(
                1f
            )

        if (index == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    drawPath(path, color = lineColor, style = Stroke(width = strokeWidth))

    val labelCount = 5
    for (i in 0 until labelCount) {
        val labelValue = minDataPoint + (maxDataPoint - minDataPoint) * (i.toFloat() / (labelCount - 1))
        val y =
            graphHeight - padding - (graphHeight - 2 * padding) * (labelValue - minDataPoint) / (maxDataPoint - minDataPoint).coerceAtLeast(
                1f
            )

        val labelText = "%.1f".format(labelValue)

        val textLayoutResult = textMeasurer.measure(
            text = labelText,
            style = TextStyle(
                color = labelColor,
                fontSize = labelTextSize.sp,
                textAlign = TextAlign.Right
            )
        )

        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = androidx.compose.ui.geometry.Offset(
                x = padding - textLayoutResult.size.width - 8.dp.toPx(), // Add some padding between the label and the graph
                y = y - (textLayoutResult.size.height / 2) // Center the text vertically
            )
        )
    }
}