package com.example.kinopoisk.extentions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun <T> Flow<T>.launchAndCollectIn(
    scope: CoroutineScope,
    context: CoroutineContext = Dispatchers.Main,
    collector: suspend (T) -> Unit
) {
    scope.launch(context) {
        collect { collector(it) }
    }
}