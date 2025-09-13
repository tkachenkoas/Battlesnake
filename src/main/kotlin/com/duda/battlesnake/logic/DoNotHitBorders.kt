package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Board
import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.GameState

fun cellIsAvailable(coordinates: Coordinates, gameState: GameState): Boolean {
    if (isHazard(coordinates, gameState.board) || isCollisionWithSnake(coordinates, gameState)) {
        return false
    }
    val (height, width) = gameState.board
    return isInsideBorders(coordinates, width, height)
}

fun isInsideBorders(coordinates: Coordinates, width: Int, height: Int): Boolean {
    val (x, y) = coordinates
    if (x < 0 || y < 0) {
        return false
    }
    return x < width && y < height
}

private fun isCollisionWithSnake(coordinates: Coordinates, gameState: GameState): Boolean {
    return gameState.board.snakes
        .any { coordinates in it.body }
}

private fun isHazard(coordinates: Coordinates, board: Board): Boolean {
    return coordinates in board.hazards
}