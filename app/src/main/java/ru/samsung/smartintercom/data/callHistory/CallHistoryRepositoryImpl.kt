package ru.samsung.smartintercom.data.callHistory

import kotlinx.coroutines.flow.Flow
import ru.samsung.smartintercom.domain.callHistory.CallHistoryDataSource
import ru.samsung.smartintercom.domain.callHistory.CallHistoryRepository
import ru.samsung.smartintercom.domain.callHistory.models.CallInfo

class CallHistoryRepositoryImpl(private val callHistoryDataSource: CallHistoryDataSource): CallHistoryRepository {
    override fun getCallHistory(): Flow<List<CallInfo>> {
        return callHistoryDataSource.getCallHistory()
    }

    override fun saveCall(callInfo: CallInfo) {
        return callHistoryDataSource.saveCall(callInfo)
    }
}