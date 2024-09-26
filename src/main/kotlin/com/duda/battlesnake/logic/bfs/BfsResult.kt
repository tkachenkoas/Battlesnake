package com.duda.battlesnake.logic.bfs

import com.duda.battlesnake.dto.Direction

data class BfsResult(
    val success: Boolean,
    val direction: Direction,
    val steps: Int
)