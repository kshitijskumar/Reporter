package com.example.reporter.model.responses

data class NewsResponseBody(
    val status: String? = null,
    val totalResults : Int = 0,
    val articles : List<Articles> = listOf()
)
