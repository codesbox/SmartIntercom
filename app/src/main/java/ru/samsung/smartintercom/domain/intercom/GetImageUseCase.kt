package ru.samsung.smartintercom.domain.intercom

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow


class GetImageUseCase(private val intercomRepository: IntercomRepository) {
    fun execute(): Flow<Bitmap?> = intercomRepository.getImage()
}