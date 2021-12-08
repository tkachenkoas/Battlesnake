package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.GameState

fun willNotHitBiggerSnakeHeadToHead(coordinates: Coordinates, gameState: GameState): Boolean {
    return gameState.board.snakes
        .none { it.head.isAdjacentTo(coordinates) && it.body.size > gameState.you.body.size }
}