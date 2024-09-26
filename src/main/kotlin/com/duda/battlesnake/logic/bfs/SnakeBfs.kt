package com.duda.battlesnake.logic.bfs

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.logic.cellIsAvailable
import com.duda.battlesnake.logic.isInsideBorders
import com.duda.battlesnake.logic.model.GameStateMatrix
import com.duda.battlesnake.logic.model.PathSearchResult
import com.duda.battlesnake.logic.model.SnakeBoardCell.EMPTY
import com.duda.battlesnake.logic.model.SnakeBoardCell.FOOD
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SnakeBfs {

    private val log = LoggerFactory.getLogger(javaClass)

    fun searchDirection(
        matrix: GameStateMatrix,
        target: Coordinates
    ): PathSearchResult {
        val head = matrix.gameState.you.head
        log.debug("Searching route from ${head} to ${target}")
        val (_, direction, steps) = bfs(matrix, head, target, HashSet(), 0)
        return PathSearchResult(direction, steps)
    }

    private fun bfs(
        matrix: GameStateMatrix,
        current: Coordinates,
        target: Coordinates,
        estimated: MutableSet<Coordinates>,
        depth: Int
    ): BfsResult {
        val directions = prioritizedDirections(current, target)

        val failedSearches = mutableListOf<BfsResult>()

        for (direction in directions) {
            log.trace("Estimating route from ${current} to ${target} via ${direction} on step ${depth}")
            val candidate: Coordinates = current.withDirection(direction)
            if (estimated.contains(candidate)) {
                failedSearches.add(BfsResult(false, direction, depth))
                continue
            }
            estimated.add(candidate)
            if (isNotAvailable(candidate, matrix)) {
                failedSearches.add(BfsResult(false, direction, depth))
                continue
            }

            if (candidate == target) {
                log.debug("Reached target ${target} via ${direction} on step ${depth}")
                return BfsResult(true, direction, depth + 1)
            }

            val candidateBfs = bfs(
                matrix, candidate, target,
                estimated, depth + 1
            )
            if (candidateBfs.success) {
                return BfsResult(true, direction, candidateBfs.steps)
            } else {
                failedSearches.add(
                    BfsResult(
                        false, direction, candidateBfs.steps
                    )
                )
            }
        }
        return failedSearches.maxByOrNull { it.steps }
            ?: run {
                val direction = directions.firstOrNull { cellIsAvailable(current, matrix.gameState) }
                    ?: directions.first()
                return BfsResult(false, direction, depth)
            }
    }

    private fun isNotAvailable(candidate: Coordinates, matrix: GameStateMatrix): Boolean {
        val (height, width) = matrix.gameState.board
        if (!isInsideBorders(candidate, width, height)) {
            return true
        }
        val (x, y) = candidate
        return matrix.cells[y][x] !in listOf(EMPTY, FOOD)
    }

}