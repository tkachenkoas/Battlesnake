package com.duda.battlesnake.dto

data class RuleSetSettings(
    val footSpawnChance: Int,
    val minimumFood : Int,
    val hazardDamagePerTurn : Int,
    val royale: Royale?,
    val squad: Squad
)
