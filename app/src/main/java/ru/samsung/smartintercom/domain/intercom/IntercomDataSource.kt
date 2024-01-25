package ru.samsung.smartintercom.domain.intercom

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface IntercomDataSource {
    fun getModel(): Flow<IntercomModel?>
    fun getImage(): Flow<Bitmap?>
}