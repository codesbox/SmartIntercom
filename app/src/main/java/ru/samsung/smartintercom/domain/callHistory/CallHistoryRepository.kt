package ru.samsung.smartintercom.domain.callHistory

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo

interface CallHistoryRepository {
    fun getCallHistory(): Flow<List<CallInfo>>
    fun saveCall(callInfo: CallInfo)
}