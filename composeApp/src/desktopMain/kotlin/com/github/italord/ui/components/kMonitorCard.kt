package com.github.italord.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.italord.model.Component
import com.github.italord.model.GPU
import com.github.italord.util.kMonitorColor
import com.github.italord.util.removeRepeatedWords
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.Image
import kmonitor.composeapp.generated.resources.*
import kmonitor.composeapp.generated.resources.Res
import kmonitor.composeapp.generated.resources.amd
import kmonitor.composeapp.generated.resources.nvidia

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
            val brandLogo = when {
                component.name.contains("Intel", ignoreCase = true) -> Res.drawable.intel
                component.name.contains("AMD", ignoreCase = true) -> Res.drawable.amd
                component.name.contains("Nvidia", ignoreCase = true) -> Res.drawable.nvidia
                else -> {
                    Res.drawable.placeholder
                }
            }
            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                kMonitorText(text = if (component is GPU) "GPU:" else "CPU:")
                kMonitorText(text = component.name.removeRepeatedWords(), fontSize = 16.sp)
                kMonitorText(text = "${component.temp}°C")
                kMonitorText(text = "Usage : ${component.load}%")
                Card(modifier = Modifier.padding(vertical = 8.dp)) {
                    Image(
                        modifier = Modifier.size(150.dp).padding(4.dp),
                        contentDescription = null,
                        painter = painterResource(brandLogo),
                    )
                }

            }
            kMonitorGraph(
                modifier = Modifier.padding(16.dp).fillMaxSize().weight(1.7f),
                dataPoints = component.temps,
                lineColor = Color.White
            )
        }
    }
}