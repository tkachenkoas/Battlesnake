package com.duda.battlesnake.logic.bfs

import com.duda.battlesnake.dto.*
import com.duda.battlesnake.logic.model.GameStateMatrix
import com.duda.battlesnake.logic.model.SnakeBoardCell
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SnakeBfsTest {

    private val underTest: SnakeBfs = SnakeBfs()

    @Test
    fun willFindSimpleDirection() {
        val matrix = matrixFromBoardMap(
            """
                EEEEEEEE
                ESEEEEFE
                EEEEEEEE    
            """.trimIndent(),
            you = BattleSnake(head = Coordinates(1, 1)),
            board = Board(width = 8, height = 3)
        )

        val direction = underTest.searchDirection(
            matrix, Coordinates(6, 1),
        )
        assertThat(direction.direction).isEqualTo(Direction.RIGHT)
        assertThat(direction.moves).isEqualTo(5)
    }

    private fun matrixFromBoardMap(
        boardMap: String,
        you: BattleSnake = BattleSnake(),
        board: Board = Board()
    ): GameStateMatrix {
        val cells = boardMap.split("\n")
            .map { lineAsArray(it.trim()) }
            .toTypedArray()

        return GameStateMatrix(
            cells, GameState(
                game = mockk(), you = you,
                board = board
            )
        )
    }

    private fun lineAsArray(line: String): Array<SnakeBoardCell> {
        return line.toCharArray()
            .map { toSnakeBoardCell(it) }
            .toTypedArray()
    }

    private fun toSnakeBoardCell(lett: Char): SnakeBoardCell {
        return SnakeBoardCell.values().first {
            it.name.startsWith(lett)
        }
    }

}