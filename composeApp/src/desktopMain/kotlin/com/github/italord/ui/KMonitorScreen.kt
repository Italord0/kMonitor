package com.github.italord.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.delay

@Composable
fun KMonitorScreen(viewModel: KMonitorViewModel) {

    val screenState = viewModel.screenState.collectAsState().value

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getHardwareInformation()
            delay(500)
        }
    }

    with(screenState) {
        Column {
            Text("CPU: ${cpu?.name}")
            Text("CPU Temperature: ${cpu?.temp}ºc")
            Text("CPU Load: ${cpu?.load}%")
            Text("------------")
            Text("GPU: ${gpu?.name}")
            Text("GPU Temperature: ${gpu?.temp}ºc")
            Text("GPU Load: ${gpu?.load}%")
            Text("Testando app rodando")
        }
    }
}