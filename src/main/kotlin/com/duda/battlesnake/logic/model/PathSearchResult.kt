package com.duda.battlesnake.logic.model

import com.duda.battlesnake.dto.Direction

data class PathSearchResult(val direction: Direction, val moves: Int)