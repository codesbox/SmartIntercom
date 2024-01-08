/* НЕ ИЗМЕНЯЙТЕ ФАЙЛ! */
/* ВСЕ ИЗМЕНЕНИЯ ПРИ ТЕСТИРОВАНИИ БУДУТ ОТМЕНЕНЫ */
package ru.samsung.smartintercom.data.call

import android.util.Log
import com.google.gson.Gson
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.isClosed
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallDataSource @Inject constructor(
    private val gson: Gson
) {
    private val _status = MutableSharedFlow<Status>(replay = 1)
    val status get() = _status.asSharedFlow()

    private var socketInfo: SocketInfo? = null


    suspend fun connect(authDto: SocketAuthDto) {
        if (socketInfo != null) return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val selectorManager = SelectorManager(Dispatchers.IO)
                val socket = aSocket(selectorManager).tcp().connect(HOSTNAME, PORT)
                socketInfo = SocketInfo(
                    socket = socket,
                    selectorManager = selectorManager,
                )

                val receiveChannel = socket.openReadChannel()
                val sendChannel = socket.openWriteChannel(autoFlush = true)
                sendChannel.writeStringUtf8("${gson.toJson(authDto)}\n")
                _status.emit(Status.Connect)
                while (!socket.isClosed) {
                    val message = receiveChannel.readUTF8Line(limit = 100)
                    if (message != null) {
                        _status.emit(Status.Message(body = message))
                    } else {
                        Log.d("Socket", "Server closed a connection")
                        close(socket = socket, selectorManager = selectorManager)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                delay(TIMEOUT)
                socketInfo = null
                _status.emit(Status.Close)
            }
        }

    }

    suspend fun close() {
        val data = socketInfo ?: return
        close(socket = data.socket, selectorManager = data.selectorManager)
    }

    private suspend fun close(
        socket: Socket,
        selectorManager: SelectorManager
    ) {
        withContext(Dispatchers.IO) {
            socket.close()
            selectorManager.close()
            socketInfo = null
            _status.emit(Status.Close)
        }
    }

    sealed interface Status {
        data object Connect : Status
        data class Message(val body: String) : Status
        data object Close : Status
    }

    private data class SocketInfo(
        val socket: Socket,
        val selectorManager: SelectorManager,
    )

    private companion object {
        const val HOSTNAME = "89.208.220.227"
        const val PORT = 9202
        const val TIMEOUT = 5000L
    }
}
