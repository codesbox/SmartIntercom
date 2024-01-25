package ru.samsung.smartintercom.domain.intercom

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface IntercomRepository {

    fun getModel(): Flow<IntercomModel?>
    fun getImage(): Flow<Bitmap?>
}