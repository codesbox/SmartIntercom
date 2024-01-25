package ru.samsung.smartintercom.data.callHistory

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.samsung.smartintercom.domain.callHistory.CallHistoryDataSource
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo

class CallHistoryDataSourceImpl(private val context: Context): CallHistoryDataSource {
    override fun getCallHistory(): Flow<List<CallInfo>> {
        return flow {

            val db = Room.databaseBuilder(
                context,
                Database::class.java, "database"
            ).build()
            db.userDao().getAll().collect{
                val result = ArrayList<CallInfo>()
                for (element in it){
                result.add(CallInfo(element.callTime, element.doorState))
            }
                this@flow.emit(result)
            }
        }
    }

    override fun saveCall(callInfo: CallInfo) {
        val db = Room.databaseBuilder(
            context,
            Database::class.java, "database"
        ).build()
        val callInfoRoom = CallInfoRoom(callTime = callInfo.callTime, doorState = callInfo.doorState)
        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().insert(callInfoRoom)
        }
    }
}