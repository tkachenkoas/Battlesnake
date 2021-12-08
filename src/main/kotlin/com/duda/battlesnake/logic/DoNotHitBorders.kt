package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Board
import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.GameState

fun cellIsAvailable(coordinates: Coordinates, gameState: GameState): Boolean {
    if (isHazard(coordinates, gameState.board) || isCollisionWithSnake(coordinates, gameState)) {
        return false;
    }
    return isInsideBorders(coordinates, gameState.board)
}

private fun isCollisionWithSnake(coordinates: Coordinates, gameState: GameState): Boolean {
    if (coordinates in gameState.you.body) {
        return true
    }
    return gameState.board.snakes
        .any { coordinates in it.body }
}

private fun isInsideBorders(coordinates: Coordinates, board: Board): Boolean {
    val (x, y) = coordinates
    if (x < 0 || y < 0) {
        return false
    }
    return x < board.width && y < board.height
}

private fun isHazard(coordinates: Coordinates, board: Board): Boolean {
    return coordinates in board.hazards
}