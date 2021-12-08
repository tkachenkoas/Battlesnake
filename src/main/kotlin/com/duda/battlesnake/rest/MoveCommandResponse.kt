package com.duda.battlesnake.rest

data class MoveCommandResponse(
    val move: String,
    val shout: String = ""
)
