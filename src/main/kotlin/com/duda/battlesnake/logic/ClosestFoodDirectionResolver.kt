package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.Direction
import com.duda.battlesnake.dto.FoodDirection
import com.duda.battlesnake.dto.GameState

fun revolveClosestFoodDirection(gameState: GameState): FoodDirection {
    val head = gameState.you.head

    val allFood = gameState.board.food
    val closest = allFood.minByOrNull { it.distanceTo(head) } ?: Coordinates(0, 0)

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