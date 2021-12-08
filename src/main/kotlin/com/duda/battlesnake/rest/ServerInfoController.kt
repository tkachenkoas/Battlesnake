package com.duda.battlesnake.rest

import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.logic.NextMoveResolver
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ServerInfoController(
    private val nextMoveResolver: NextMoveResolver
) {

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

        return nextMoveResolver.resolveNextMove(gameState)
    }

    private fun asJson(gameState: GameState) = ObjectMapper().writeValueAsString(gameState)

}