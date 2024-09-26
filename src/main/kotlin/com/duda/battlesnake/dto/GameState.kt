package com.duda.battlesnake.dto

data class GameState(
    val game: Game,
    val turn: Int = 0,
    val board: Board,
    val you: BattleSnake
)