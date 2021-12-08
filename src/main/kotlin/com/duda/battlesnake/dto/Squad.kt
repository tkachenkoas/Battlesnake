package com.duda.battlesnake.dto

data class Squad(
    val allowBodyCollisions: Boolean,
    val sharedElimination: Boolean,
    val sharedHealth: Boolean,
    val sharedLength: Boolean
)
