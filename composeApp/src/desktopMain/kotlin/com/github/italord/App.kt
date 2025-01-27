package com.github.italord

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.github.italord.ui.KMonitorViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: KMonitorViewModel) {

    val screenState = viewModel.screenState.collectAsState().value

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getTemperatures()
            delay(1000)
        }
    }

    with(screenState) {
        Column {
            Text("Motherboard: ${mobo?.name}")
            Text("Motherboard Temperature: ${mobo?.temp}")
            Text("CPU: ${cpu?.name}")
            Text("CPU Temperature: ${cpu?.temp}")
            Text("GPU: ${gpu?.name}")
            Text("GPU Temperature: ${gpu?.temp}")
        }
    }
}