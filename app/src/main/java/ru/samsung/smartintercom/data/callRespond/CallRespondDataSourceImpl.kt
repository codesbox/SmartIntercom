package ru.samsung.smartintercom.data.callRespond

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import ru.samsung.smartintercom.data.callRespond.APIService
import ru.samsung.smartintercom.data.utils.RetrofitBuilder
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.callHistory.models.DoorState
import ru.samsung.smartintercom.domain.callRespond.CallRespondDataSource
import ru.samsung.smartintercom.domain.callRespond.models.CallRespondStatus

class CallRespondDataSourceImpl(private val getAuthDataUseCase: GetAuthDataUseCase): CallRespondDataSource {
    override fun respond(doorState: DoorState): Flow<CallRespondStatus> {
        return flow {
            try {
                    val apiService = RetrofitBuilder.retrofit.create(APIService::class.java)
                    val authData = getAuthDataUseCase.execute()

                    val response = when (doorState) {
                        DoorState.OPEN -> apiService.sendStatus(authData.room, authData.house, CallRespondRequestBody("open"))
                        DoorState.CLOSE -> apiService.sendStatus(authData.room, authData.house, CallRespondRequestBody("close"))
                    }

                    if (response.isEmpty()) {
                        Log.d("HelloB", response)
                        emit(CallRespondStatus.BAD_REQUEST)
                    } else {
                        Log.d("Hello", response)
                        emit(CallRespondStatus.OK)
                    }


            } catch (e: Exception) {
                e.printStackTrace()
                emit(CallRespondStatus.BAD_REQUEST)
            }
        }
    }
}