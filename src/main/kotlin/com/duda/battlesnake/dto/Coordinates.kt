package com.duda.battlesnake.dto

import java.awt.geom.Point2D.distance

data class Coordinates(
    val x: Int,
    val y: Int
) {
    fun distanceTo(other: Coordinates): Double {
        return distance(x.toDouble(), y.toDouble(), other.x.toDouble(), other.y.toDouble())
    }

    fun withDirection(direction: Direction): Coordinates {
        return when (direction) {
            Direction.UP -> Coordinates(x, y + 1)
            Direction.DOWN -> Coordinates(x, y - 1)
            Direction.LEFT -> Coordinates(x - 1, y)
            else -> Coordinates(x + 1, y)
        }
    }
}