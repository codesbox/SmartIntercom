/* НЕ ИЗМЕНЯЙТЕ ФАЙЛ! */
/* ВСЕ ИЗМЕНЕНИЯ ПРИ ТЕСТИРОВАНИИ БУДУТ ОТМЕНЕНЫ */
package ru.samsung.smartintercom.data.call

import android.util.Log
import dagger.Reusable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.auth.model.AuthEntity
import ru.samsung.smartintercom.domain.call.CallRepository
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@Reusable
class CallRepositoryImpl @Inject constructor(
    authRepo: AuthRepository,
    private val callDataSource: CallDataSource,
) : CallRepository {
    override val intercomCallStart: Flow<Unit> = authRepo.authData
        .flatMapLatest { data ->
            if (data == null) {
                callDataSource.close()
            } else {
                reconnectSocket(data)
            }
            callDataSource.status.map { status -> status to data }
        }
        .mapNotNull { (status, authData) ->
            when (status) {
                is CallDataSource.Status.Close -> {
                    Log.d(TAG, "Close")
                    if (authData != null) reconnectSocket(authData)
                    null
                }
                is CallDataSource.Status.Connect -> {
                    Log.d(TAG, "Connect")
                    null
                }
                is CallDataSource.Status.Message -> {
                    Log.d(TAG, "Message")
                    Unit
                }
            }
        }
        .buffer(
            capacity = 0,
            BufferOverflow.DROP_OLDEST
        )

    private suspend fun reconnectSocket(authEntity: AuthEntity) {
        callDataSource.connect(SocketAuthDto(house = authEntity.house, room = authEntity.room))
    }

    private companion object {
        const val TAG = "SOCKET"
    }
}
