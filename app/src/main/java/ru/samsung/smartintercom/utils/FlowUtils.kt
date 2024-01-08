package ru.samsung.smartintercom.utils

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> MutablePublishFlow() = MutableSharedFlow<T>(
    replay = 0,
    extraBufferCapacity = 1,
    BufferOverflow.DROP_OLDEST
)
