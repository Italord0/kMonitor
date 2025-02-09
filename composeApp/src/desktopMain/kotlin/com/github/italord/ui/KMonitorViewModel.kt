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
            val temperature = cpu.sensors.temperatures.map { it.value }.average().toInt()
            if (_screenState.value.cpu.temps.size > 10) {
                _screenState.value.cpu.temps.removeAt(0)
            }
            _screenState.value.cpu.temps.add(temperature.toFloat())
            CPU(
                cpu.name,
                temperature,
                cpu.sensors.loads.first { it.name == "Load CPU Total" }.value.toInt(),
                temps = _screenState.value.cpu.temps
            )
        }
        if (cpu.isNotEmpty()) {
            _screenState.update { screenState ->
                screenState.copy(cpu = cpu.first())
            }
        }

        //GPU
        val gpu = JSensors.get.components().gpus.map { gpu ->
            val temperatureGpu = gpu.sensors.temperatures.map { it.value }.average().toInt()
            if (_screenState.value.gpu.temps.size > 10) {
                _screenState.value.gpu.temps.removeAt(0)
            }
            _screenState.value.gpu.temps.add(temperatureGpu.toFloat())
            GPU(
                gpu.name,
                temperatureGpu,
                gpu.sensors.loads.map { it.value }.first().toInt(),
                temps = _screenState.value.gpu.temps
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
                    mutableListOf(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f)
                ))
            }

        //GPU
        _screenState.update { screenState ->
            screenState.copy(gpu = GPU(
                "Fake GPU",
                67,
                69,
                mutableListOf(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f)
            ))
        }
    }
}