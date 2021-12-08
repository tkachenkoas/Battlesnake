package com.duda.battlesnake.dto

data class BattleSnake(
    val id: String,
    val name: String,
    val health: Int,
    val body: List<Coordinates>,
    val latency: Int,
    val head: Coordinates,
    val length: Int,
    val shout: String,
    val squad: Int
)