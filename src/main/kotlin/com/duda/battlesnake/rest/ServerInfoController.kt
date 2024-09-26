package com.duda.battlesnake.rest

import com.duda.battlesnake.dto.GameState
import com.duda.battlesnake.logic.NextMoveResolver
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory.getLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ServerInfoController(
    private val nextMoveResolver: NextMoveResolver
) {

    private val log = getLogger(javaClass)

    @GetMapping("/")
    fun getServerInfo(): SnakeInfoResponse {
        return SnakeInfoResponse()
    }

    @PostMapping("/start")
    fun startGame(@RequestBody gameState: GameState) {
        log.debug("Game start request: ${asJson(gameState)}")
    }

    @PostMapping("/end")
    fun endGame(@RequestBody gameState: GameState) {
        log.debug("Game end request: ${asJson(gameState)}")
    }

    @PostMapping("/move")
    fun moveSnakeEndpoint(@RequestBody gameState: GameState): MoveCommandResponse {
        log.debug("Move snake request: ${asJson(gameState)}")
        val nextMove = nextMoveResolver.bfsNextMove(gameState)
        log.debug("Move command: ${asJson(nextMove)}")
        return nextMove
    }

    private fun asJson(gameState: Any) = ObjectMapper().writeValueAsString(gameState)

}