package com.github.italord.ui

import com.github.italord.model.CPU
import com.github.italord.model.GPU
import com.github.italord.model.KMonitorScreenState
import com.github.italord.model.MOBO
import com.profesorfalken.jsensors.JSensors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import oshi.SystemInfo

class KMonitorViewModel {

    private val _screenState: MutableStateFlow<KMonitorScreenState> = MutableStateFlow(KMonitorScreenState())
    val screenState = _screenState.asStateFlow()

    private var systemInfo : SystemInfo = SystemInfo()

    fun getTemperatures(){

        //MOBO
        val mobo = JSensors.get.components().mobos.map { mobo -> MOBO(mobo.name.orEmpty(),mobo.sensors.temperatures.map { it.value }.average().toInt()) }
        _screenState.update { screenState ->
            screenState.copy(mobo = mobo.first())
        }

        //CPU
        val cpu = JSensors.get.components().cpus.map { cpu -> CPU(cpu.name,cpu.sensors.temperatures.map { it.value }.average().toInt()) }
        _screenState.update { screenState ->
            screenState.copy(cpu = cpu.first())
        }

        //GPU
        val gpu = JSensors.get.components().gpus.map { gpu -> GPU(gpu.name,gpu.sensors.temperatures.map { it.value }.average().toInt()) }
        _screenState.update { screenState ->
            screenState.copy(gpu = gpu.first())
        }

    }
}