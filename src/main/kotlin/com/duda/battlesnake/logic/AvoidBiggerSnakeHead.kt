package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.GameState

fun willNotHitBiggerSnakeHeadToHead(coordinates: Coordinates, gameState: GameState): Boolean {
    val yourSnake = gameState.you
    return gameState.board.snakes
        .filter { it.id != yourSnake.id }
        .none { it.head.isAdjacentTo(coordinates) && it.body.size >= yourSnake.body.size }
}