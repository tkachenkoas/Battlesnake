package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.Direction
import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.logic.bfs.SnakeBfs
import com.duda.battlesnake.rest.MoveCommandResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class NextMoveResolver(
    private val snakeBfs: SnakeBfs,
    private val matrixCreator: GameStateMatrixCreator
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun bfsNextMove(gameState: GameState): MoveCommandResponse {
        val stateMatrix = matrixCreator.createGameStateMatrix(gameState)
        val target = getTargetCell(gameState)

        val searchResult = snakeBfs.searchDirection(stateMatrix, target)
        return toMoveCommand(
            searchResult.direction
        )
    }

    private fun getTargetCell(gameState: GameState): Coordinates {
        val coordinates = closestFood(gameState)
        if (cellIsAvailable(coordinates, gameState)) {
            return coordinates
        }
        return getTargetCell(gameState)
    }

    fun resolveNextMove(gameState: GameState): MoveCommandResponse {
        val current = gameState.you.head
        val (first, second) = revolveClosestFoodDirection(gameState)

        val toFood = setOf(first, second)

        val safeDirectionToFood = toFood.firstOrNull { seemsLikeAGoodMove(current, gameState, it) }
        safeDirectionToFood?.let {
            return toMoveCommand(it)
        }

        val notToFood = Direction.values()
            .filterNot { it == first || it == second }
            .toMutableList()

        val result = notToFood.firstOrNull { seemsLikeAGoodMove(current, gameState, it) }
            ?: Direction.UP // we lost any way :(

        return toMoveCommand(result)
    }

    private fun seemsLikeAGoodMove(current: Coordinates, gameState: GameState, direction: Direction): Boolean {
        val cellToGoTo = current.withDirection(direction)
        return cellIsAvailable(cellToGoTo, gameState)
                && willNotHitBiggerSnakeHeadToHead(cellToGoTo, gameState)
    }

    private fun toMoveCommand(safeDirectionToFood: Direction): MoveCommandResponse {
        return MoveCommandResponse(safeDirectionToFood.value)
    }

}