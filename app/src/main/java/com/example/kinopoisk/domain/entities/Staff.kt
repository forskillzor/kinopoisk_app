package com.example.kinopoisk.domain.entities

enum class StaffType {
    WRITER, OPERATOR, EDITOR, COMPOSER, PRODUCER_USSR,
    TRANSLATOR, DIRECTOR, DESIGN, PRODUCER, ACTOR,
    VOICE_DIRECTOR, UNKNOWN
}

data class Staff(
    val id: Int,
    val name: String,
    val description: String,
    val posterUrl: String,
    val professionText: String,
    val professionKey: StaffType
)