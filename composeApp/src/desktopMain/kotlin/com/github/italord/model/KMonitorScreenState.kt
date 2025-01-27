package com.github.italord.model

data class KMonitorScreenState(
    val cpu: CPU? = null,
    val gpu: GPU? = null,
    val mobo: MOBO? = null,
)

data class CPU(
    val name: String,
    val temp: Int,
)

data class GPU(
    val name: String,
    val temp: Int,
)

data class MOBO(
    val name: String,
    val temp: Int,
)
