package com.duda.battlesnake.logic.bfs

import com.duda.battlesnake.dto.Coordinates
import com.duda.battlesnake.dto.Direction

fun prioritizedDirections(
    current: Coordinates,
    to: Coordinates
): List<Direction> {
    val prioritizedDirections = Direction.values().copyOf()
    return prioritizedDirections.sortedWith(
        compareBy { current.withDirection(it).stepsTo(to) }
    )
}