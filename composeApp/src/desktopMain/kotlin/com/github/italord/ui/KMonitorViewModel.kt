package com.github.italord.ui

import com.github.italord.model.CPU
import com.github.italord.model.GPU
import com.github.italord.model.KMonitorScreenState
import com.profesorfalken.jsensors.JSensors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KMonitorViewModel {

    private val _screenState: MutableStateFlow<KMonitorScreenState> = MutableStateFlow(KMonitorScreenState())
    val screenState = _screenState.asStateFlow()

    fun getHardwareInformation() {

        //CPU
        val cpu = JSensors.get.components().cpus.map { cpu ->
            CPU(
                cpu.name,
                cpu.sensors.temperatures.map { it.value }.average().toInt(),
                cpu.sensors.loads.first { it.name == "Load CPU Total" }.value.toInt(),
                listOf(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f)
            )
        }
        if (cpu.isNotEmpty()) {
            _screenState.update { screenState ->
                screenState.copy(cpu = cpu.first())
            }
        }

        //GPU
        val gpu = JSensors.get.components().gpus.map { gpu ->
            GPU(
                gpu.name,
                gpu.sensors.temperatures.map { it.value }.average().toInt(),
                gpu.sensors.loads.map { it.value }.first().toInt(),
                listOf(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f)
            )
        }
        if (gpu.isNotEmpty()) {
            _screenState.update { screenState ->
                screenState.copy(gpu = gpu.first())
            }
        }

    }

    fun fakeHardwareInformation(){
        //CPU
            _screenState.update { screenState ->
                screenState.copy(cpu = CPU(
                    "Fake CPU",
                    67,
                    69,
                    listOf(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f)
                ))
            }

        //GPU
        _screenState.update { screenState ->
            screenState.copy(gpu = GPU(
                "Fake GPU",
                67,
                69,
                listOf(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f)
            ))
        }
    }
}