package com.github.italord.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.github.italord.ui.components.kMonitorCard
import com.github.italord.util.kMonitorColor
import kotlinx.coroutines.delay

@Composable
fun KMonitorScreen(viewModel: KMonitorViewModel) {

    val screenState = viewModel.screenState.collectAsState().value

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getHardwareInformation()
            delay(2000)
        }
    }

    with(screenState) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(kMonitorColor.backgroundColor)
                .fillMaxSize()
        ) {
            kMonitorCard(modifier = Modifier.weight(1f), component = cpu)
            kMonitorCard(modifier = Modifier.weight(1f), component = gpu)
        }
    }
}