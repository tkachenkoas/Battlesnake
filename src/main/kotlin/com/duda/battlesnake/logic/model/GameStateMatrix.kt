package com.duda.battlesnake.logic.model

import com.duda.battlesnake.dto.GameState

class GameStateMatrix(
    val cells: Array<Array<SnakeBoardCell>>,
    val gameState: GameState,
)