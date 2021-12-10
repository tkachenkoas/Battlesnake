package com.duda.battlesnake.dto

data class BattleSnake(
    val id: String = "",
    val name: String = "",
    val health: Int = 0,
    val body: List<Coordinates> = listOf(),
    val latency: Int = 500,
    val head: Coordinates = Coordinates(0, 0),
    val length: Int = 0,
    val shout: String = "",
    val squad: Int = 0
)