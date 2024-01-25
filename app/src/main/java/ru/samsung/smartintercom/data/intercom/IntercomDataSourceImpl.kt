package ru.samsung.smartintercom.data.intercom

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.samsung.smartintercom.data.utils.RetrofitBuilder
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.intercom.IntercomDataSource
import ru.samsung.smartintercom.domain.intercom.IntercomModel

class IntercomDataSourceImpl(private val authDataSource: AuthDataSource): IntercomDataSource {
    override fun getModel(): Flow<IntercomModel?> {
        return flow {
            try {
                val apiService = RetrofitBuilder.retrofit.create(APIService::class.java)
                val response = apiService.getModel(authDataSource.getAuthData().house, authDataSource.getAuthData().room)
                emit(response)
            }
            catch(e: Exception){
                emit(null)
            }
        }
    }

    override fun getImage(): Flow<Bitmap?> {
        return flow {
            try{
                val apiService = RetrofitBuilder.retrofit.create(APIService::class.java)
                val response = apiService.getImage(authDataSource.getAuthData().house, authDataSource.getAuthData().room)
                val responseBody = response.body()
                if (responseBody != null) {
                    val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                    emit(bitmap)
                } else {
                    emit(null)
                }
            }
            catch (e: Exception){
                emit(null)
            }
        }
    }
}