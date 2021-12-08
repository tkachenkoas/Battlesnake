package com.duda.battlesnake.dto

data class Game(
    val id: String,
    val ruleset: Ruleset,
    val timeout: Int,
    val source: String
)