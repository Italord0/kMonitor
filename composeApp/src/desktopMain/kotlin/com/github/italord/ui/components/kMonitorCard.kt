package com.github.italord.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.italord.model.Component
import com.github.italord.model.GPU
import com.github.italord.util.kMonitorColor

@Composable
fun kMonitorCard(
    modifier: Modifier = Modifier,
    component: Component
) {
    Card(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        backgroundColor = kMonitorColor.ascentColor
    ) {
        Row {
            Column(modifier = Modifier.padding(16.dp)) {
                kMonitorText(text = if (component is GPU) "GPU:" else "CPU:")
                kMonitorText(text = component.name)
                kMonitorText(text = "${component.temp}°C")
                kMonitorText(text = "Usage : ${component.load}%")
            }
            kMonitorGraph(
                dataPoints = component.temps,
                modifier = Modifier.fillMaxSize(),
                lineColor = Color.White
            )
        }
    }
}