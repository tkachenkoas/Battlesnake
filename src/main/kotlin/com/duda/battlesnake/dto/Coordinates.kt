package com.duda.battlesnake.dto

import java.awt.geom.Point2D.distance
import kotlin.math.abs

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

    fun isAdjacentTo(other: Coordinates): Boolean {
        if (this.x == other.x) {
            return abs(this.y - other.y) == 1
        }
        if (this.y == other.y) {
            return abs(this.x - other.x) == 1
        }
        return false
    }
}