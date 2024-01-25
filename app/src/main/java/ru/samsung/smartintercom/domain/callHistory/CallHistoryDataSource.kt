package ru.samsung.smartintercom.domain.callHistory

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo

interface CallHistoryDataSource {
    fun getCallHistory(): Flow<List<CallInfo>>
    fun saveCall(callInfo: CallInfo)
}