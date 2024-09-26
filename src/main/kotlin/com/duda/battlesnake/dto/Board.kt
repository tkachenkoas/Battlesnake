package com.duda.battlesnake.dto

data class Board(
    val height: Int = 0,
    val width: Int = 0,
    val food: List<Coordinates> = listOf(),
    val hazards: List<Coordinates> = listOf(),
    val snakes: List<BattleSnake> = listOf()
)
