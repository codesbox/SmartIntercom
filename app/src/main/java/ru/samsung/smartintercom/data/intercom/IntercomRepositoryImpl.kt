package ru.samsung.smartintercom.data.intercom

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.intercom.IntercomDataSource
import ru.samsung.smartintercom.domain.intercom.IntercomModel
import ru.samsung.smartintercom.domain.intercom.IntercomRepository

class IntercomRepositoryImpl(private val intercomDataSource: IntercomDataSource): IntercomRepository {
    override fun getModel(): Flow<IntercomModel?> {
        return intercomDataSource.getModel()
    }

    override fun getImage(): Flow<Bitmap?> {
        return intercomDataSource.getImage()
    }
}