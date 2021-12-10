package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.*

fun revolveClosestFoodDirection(gameState: GameState): FoodDirection {
    val head = gameState.you.head

    val closest = closestFood(gameState)

    val first: Direction = when {
        (head.x == closest.x) -> if (head.y > closest.y) {
            Direction.DOWN
        } else Direction.UP
        (head.x > closest.x) -> Direction.LEFT
        else -> Direction.RIGHT
    }

    val second: Direction = when {
        (head.y == closest.y) -> if (head.x > head.y) {
            Direction.LEFT
        } else Direction.RIGHT
        (head.y > closest.y) -> Direction.DOWN
        else -> Direction.UP
    }

    return FoodDirection(
        first, second
    )
}

fun closestFood(
    gameState: GameState
): Coordinates {
    val allFood = gameState.board.food
    val head = gameState.you.head
    return allFood.minByOrNull { it.stepsTo(head) } ?: randomCorner(gameState.board)
}

fun randomCorner(board: Board): Coordinates {
    return Coordinates(
        listOf(0, board.width - 1).random(),
        listOf(0, board.height - 1).random()
    )
}