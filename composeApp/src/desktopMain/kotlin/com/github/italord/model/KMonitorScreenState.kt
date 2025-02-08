package com.github.italord.model


data class KMonitorScreenState(
    val cpu: Component = CPU("null", 0,0, listOf()),
    val gpu: Component = GPU("null", 0,0, listOf())
)

interface Component {
    val name: String
    val temp: Int
    val load: Int
    val temps : List<Float>
}

data class CPU(
    override val name: String,
    override val temp: Int,
    override val load: Int,
    override val temps: List<Float>
) : Component

data class GPU(
    override val name: String,
    override val temp: Int,
    override val load: Int,
    override val temps: List<Float>
) : Component
