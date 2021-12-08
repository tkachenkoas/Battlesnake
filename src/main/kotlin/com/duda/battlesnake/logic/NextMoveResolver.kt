package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.Direction
import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.rest.MoveCommandResponse
import org.springframework.stereotype.Component

@Component
class NextMoveResolver {
    fun resolveNextMove(gameState: GameState): MoveCommandResponse {
        val current = gameState.you.head
        val (first, second) = revolveClosestFoodDirection(gameState)

        val toFood = setOf(first, second)

        val safeDirectionToFood = toFood.firstOrNull{ seemsLikeAGoodMove(current, gameState, it) }
        safeDirectionToFood?.let {
            return logAndReturn(it)
        }

        val notToFood = Direction.values()
            .filterNot { it == first || it == second }
            .toMutableList()

        val result = notToFood.firstOrNull { seemsLikeAGoodMove(current, gameState, it) }
            ?: Direction.UP // we lost any way :(

        return logAndReturn(result)
    }

    private fun seemsLikeAGoodMove(current: Coordinates, gameState: GameState, direction: Direction): Boolean {
        val cellToGoTo = current.withDirection(direction)
        return cellIsAvailable(cellToGoTo, gameState)
                && willNotHitBiggerSnakeHeadToHead(cellToGoTo, gameState)
    }

    private fun logAndReturn(safeDirectionToFood: Direction): MoveCommandResponse {
        return MoveCommandResponse(safeDirectionToFood.value)
    }

}