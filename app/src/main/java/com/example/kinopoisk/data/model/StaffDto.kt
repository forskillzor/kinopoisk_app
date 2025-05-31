package com.example.kinopoisk.data.model

enum class StaffTypeDto {
    WRITER, OPERATOR, EDITOR, COMPOSER, PRODUCER_USSR,
    TRANSLATOR, DIRECTOR, DESIGN, PRODUCER, ACTOR,
    VOICE_DIRECTOR, UNKNOWN
}

data class StaffDto(
    val staffId: Int,
    val nameRu: String,
    val nameEn: String,
    val description: String?,
    val posterUrl: String,
    val professionText: String,
    val professionKey: StaffTypeDto
)
