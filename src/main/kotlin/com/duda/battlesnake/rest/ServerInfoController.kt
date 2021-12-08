package com.duda.battlesnake.rest

import com.duda.battlesnake.dto.Commands
import com.duda.battlesnake.dto.GameState
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
        println("Game start request: $gameState")
    }

    @PostMapping("/end")
    fun endGame(@RequestBody gameState: GameState) {
        println("Game end request: $gameState")
    }

    @PostMapping("/move")
    fun moveSnakeEndpoint(@RequestBody gameState: GameState): MoveCommandResponse {
        println("Move snake request: $gameState")
        return MoveCommandResponse(
            Commands.values().random().value
        )
    }

}