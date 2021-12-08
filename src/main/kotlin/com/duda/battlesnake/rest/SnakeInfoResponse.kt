package com.duda.battlesnake.rest

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = ANY)
data class SnakeInfoResponse(
    @JsonProperty("apiversion") val apiVersion: String = "1",
    val author: String = "MyUsername",
    val color: String = "#888888",
    val head: String = "silly",
    val tail: String = "curled",
    val version: String = "1.2"
)