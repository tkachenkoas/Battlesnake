package com.duda.battlesnake.logic

import com.duda.battlesnake.dto.BattleSnake
import com.duda.battlesnake.dto.Board
import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.logic.model.GameStateMatrix
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameStateMatrixCreatorTest {

    private val underTest: GameStateMatrixCreator = GameStateMatrixCreator()

    @Test
    fun willInitBoard() {
        val gameState = GameState(
            mockk(), 1, Board(
                2, 3
            ), BattleSnake()
        )
        val matrix = underTest.createGameStateMatrix(gameState)
        val asString = asMultilineString(matrix)
        assertThat(asString).isEqualTo(
            """
               EEE
               SEE
            """.trimIndent()
        )
    }

    @Test
    fun willMarkFood() {
        val gameState = GameState(
            mockk(), 1, Board(
                4, 3, food = listOf(
                    Coordinates(1, 0), Coordinates(1, 1)
                )
            ), BattleSnake()
        )
        val matrix = underTest.createGameStateMatrix(gameState)
        val asString = asMultilineString(matrix)
        assertThat(asString).isEqualTo(
            """
               EEE
               EEE
               EFE
               SFE
            """.trimIndent()
        )
    }

    @Test
    fun willMarkHazards() {
        val gameState = GameState(
            mockk(), 1, Board(
                2, 3, hazards = listOf(
                    Coordinates(1, 0), Coordinates(1, 1)
                )
            ), BattleSnake()
        )
        val matrix = underTest.createGameStateMatrix(gameState)
        val asString = asMultilineString(matrix)
        assertThat(asString).isEqualTo(
            """
               EHE
               SHE
            """.trimIndent()
        )
    }

    @Test
    fun willMarkSelfOnBoard() {
        val gameState = GameState(
            mockk(), 1, Board(
                4, 5
            ), BattleSnake(
                head = Coordinates(0, 1), body = listOf(
                    Coordinates(1, 1), Coordinates(1, 2),
                    Coordinates(2, 2),
                )
            )
        )
        val matrix = underTest.createGameStateMatrix(gameState)
        val asString = asMultilineString(matrix)
        assertThat(asString).isEqualTo(
            """
               EEEEE
               ESSEE
               SSEEE
               EEEEE
            """.trimIndent()
        )
    }

    @Test
    fun willMarkOtherSnakeOnBoard() {
        val gameState = GameState(
            mockk(), 1, Board(
                4, 5, snakes = listOf(
                    BattleSnake(
                        id = "123",
                        head = Coordinates(0, 1), body = listOf(
                            Coordinates(1, 1), Coordinates(1, 2),
                            Coordinates(2, 2),
                        )
                    )
                )
            ), BattleSnake()
        )
        val matrix = underTest.createGameStateMatrix(gameState)
        val asString = asMultilineString(matrix)
        assertThat(asString).isEqualTo(
            """
               EEEEE
               EOOEE
               OOEEE
               SEEEE
            """.trimIndent()
        )
    }

    private fun asMultilineString(matrix: GameStateMatrix): String {
        return matrix.cells.reversed().joinToString(separator = "\n", transform = {
            it.joinToString("", transform = { cell -> "${cell.name.first()}" })
        })
    }

}