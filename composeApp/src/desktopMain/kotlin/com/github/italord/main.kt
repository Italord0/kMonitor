package com.github.italord

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.italord.ui.KMonitorViewModel

fun main() = application {

    val viewModel = KMonitorViewModel()

    Window(
        onCloseRequest = ::exitApplication,
        title = "kMonitor",
    ) {
        App(viewModel)
    }
}