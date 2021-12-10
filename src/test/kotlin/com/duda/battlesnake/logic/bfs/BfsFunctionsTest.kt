package com.duda.battlesnake.logic.bfs

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.Direction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BfsFunctionsTest {

    @Test
    fun targetCellIsToRightTest() {
        val directions = prioritizedDirections(
            Coordinates(0, 1),
            Coordinates(3, 1),
        )

        assertThat(directions.first()).isEqualTo(Direction.RIGHT)
    }

    @Test
    fun targetCellIsUpTest() {
        val directions = prioritizedDirections(
            Coordinates(0, 2),
            Coordinates(0, 5),
        )

        assertThat(directions.first()).isEqualTo(Direction.UP)
    }

    @Test
    fun targetCellIsUpRightTest() {
        val directions = prioritizedDirections(
            Coordinates(0, 2),
            Coordinates(3, 5),
        )

        assertThat(directions.first()).isIn(Direction.UP, Direction.RIGHT)
        assertThat(directions[1]).isIn(Direction.UP, Direction.RIGHT)
    }

}