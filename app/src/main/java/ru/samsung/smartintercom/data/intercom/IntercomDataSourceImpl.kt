package ru.samsung.smartintercom.data.intercom

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import ru.samsung.smartintercom.data.utils.RetrofitBuilder
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.intercom.IntercomDataSource
import ru.samsung.smartintercom.domain.intercom.IntercomModel
import kotlin.time.Duration.Companion.seconds

class IntercomDataSourceImpl(private val getAuthDataUseCase: GetAuthDataUseCase) :
    IntercomDataSource {
    override fun getModel(): Flow<IntercomModel?> {
        return flow {
            try {
                val apiService = RetrofitBuilder.retrofit.create(APIService::class.java)
                val response = apiService.getModel(
                    getAuthDataUseCase.execute().house,
                    getAuthDataUseCase.execute().room
                )
                emit(response)
            } catch (e: Exception) {
                emit(null)
            }
        }
    }
    
    override fun getImage(): Flow<Bitmap?> {
        return flow {
            try {
                withTimeout(10.seconds) {
                    val apiService = RetrofitBuilder.retrofit.create(APIService::class.java)
                    val response = apiService.getImage(
                        getAuthDataUseCase.execute().house,
                        getAuthDataUseCase.execute().room
                    )
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                        emit(bitmap)
                    } else {
                        emit(null)
                    }
                }
            }
            catch (e: TimeoutCancellationException){
                throw e
            }
            catch (e: Exception) {
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }
}