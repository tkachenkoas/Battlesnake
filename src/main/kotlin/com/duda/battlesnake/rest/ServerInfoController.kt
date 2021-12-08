package com.duda.battlesnake.rest

import com.duda.battlesnake.dto.Direction
import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.logic.cellIsSafeToGo
import com.duda.battlesnake.logic.revolveClosestFoodDirection
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ServerInfoController {

    @GetMapping("/")
    fun getServerInfo(): ServerInfoDto {
        return ServerInfoDto()
    }

    @PostMapping("/start")
    fun startGame(@RequestBody gameState: GameState) {
        println("Game start request: ${asJson(gameState)}")
    }

    @PostMapping("/end")
    fun endGame(@RequestBody gameState: GameState) {
        println("Game end request: ${asJson(gameState)}")
    }

    @PostMapping("/move")
    fun moveSnakeEndpoint(@RequestBody gameState: GameState): MoveCommandResponse {
        // println("Move snake request: ${asJson(gameState)}")

        val current = gameState.you.head
        val (first, second) = revolveClosestFoodDirection(gameState)

        val toFood = setOf(first, second)

        val safeDirectionToFood = toFood.firstOrNull { cellIsSafeToGo(current.withDirection(it), gameState) }
        safeDirectionToFood?.let {
            return logAndReturn(it)
        }

        val notToFood = Direction.values()
            .filterNot { it == first || it == second }
            .toMutableList()

        val result = notToFood.firstOrNull { cellIsSafeToGo(current.withDirection(it), gameState) }
            ?: Direction.UP // we lost any way :(

        return logAndReturn(result)
    }

    private fun logAndReturn(safeDirectionToFood: Direction): MoveCommandResponse {
        println(safeDirectionToFood.value)
        return MoveCommandResponse(safeDirectionToFood.value)
    }

    private fun asJson(gameState: GameState) = ObjectMapper().writeValueAsString(gameState)

}