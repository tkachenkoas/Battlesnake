package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.BattleSnake
import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.logic.model.GameStateMatrix
import com.duda.battlesnake.logic.model.SnakeBoardCell
import org.springframework.stereotype.Component

@Component
class GameStateMatrixCreator {

    fun createGameStateMatrix(gameState: GameState): GameStateMatrix {
        val board = gameState.board

        val cells = Array(board.height) {
            Array(board.width) { SnakeBoardCell.EMPTY }
        }

        val matrix = GameStateMatrix(cells, gameState)
        markCellsAs(board.food, matrix, SnakeBoardCell.FOOD)
        markCellsAs(board.hazards, matrix, SnakeBoardCell.HAZARD)

        markSnakeOnBoard(matrix, gameState.you, SnakeBoardCell.SELF)
        board.snakes
            .filter { it.id != gameState.you.id }
            .forEach { markSnakeOnBoard(matrix, it, SnakeBoardCell.OTHER) }
        return matrix
    }

    private fun markSnakeOnBoard(
        matrix: GameStateMatrix,
        snake: BattleSnake, value: SnakeBoardCell
    ) {
        val (x, y) = snake.head
        matrix.cells[y][x] = value
        val body = snake.body
        markCellsAs(body, matrix, value)
    }

    private fun markCellsAs(
        body: List<Coordinates>,
        matrix: GameStateMatrix,
        value: SnakeBoardCell
    ) {
        body.forEach { matrix.cells[it.y][it.x] = value }
    }

}