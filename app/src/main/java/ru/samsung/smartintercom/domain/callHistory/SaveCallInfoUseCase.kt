package ru.samsung.smartintercom.domain.callHistory


import ru.samsung.smartintercom.domain.callHistory.models.CallInfo


class SaveCallInfoUseCase(private val callHistoryRepository: CallHistoryRepository) {

    fun execute(callInfo: CallInfo) {
        callHistoryRepository.saveCall(callInfo)
    }

}