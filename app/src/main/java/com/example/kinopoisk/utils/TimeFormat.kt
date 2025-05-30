package com.example.kinopoisk.utils

fun formatMinutesToHoursAndMinutes(totalMinutes: Int): String {
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return buildString {
        if (hours > 0) append("$hours ч ")
        if (minutes > 0) append("$minutes мин")
    }
}

fun formatAgeLimit(ageLimit: String): String {
    val digits = ageLimit.filter { it.isDigit() }
    return if (digits.isNotEmpty()) "$digits+" else ""
}