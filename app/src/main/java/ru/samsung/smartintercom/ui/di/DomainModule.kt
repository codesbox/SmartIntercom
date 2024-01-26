package ru.samsung.smartintercom.ui.di

import com.google.gson.Gson
import org.koin.dsl.module
import ru.samsung.smartintercom.domain.auth.GetAuthDataUseCase
import ru.samsung.smartintercom.domain.auth.SendDataToSharedFlowUseCase
import ru.samsung.smartintercom.domain.auth.SetAuthDataUseCase
import ru.samsung.smartintercom.domain.call.GetCallNeededUseCase
import ru.samsung.smartintercom.domain.callHistory.GetCallHistoryUseCase
import ru.samsung.smartintercom.domain.callHistory.SaveCallInfoUseCase
import ru.samsung.smartintercom.domain.intercom.GetImageUseCase
import ru.samsung.smartintercom.domain.intercom.GetModelUseCase

val domainModule = module {
    single<Gson>{
        Gson()
    }
    factory<GetCallNeededUseCase> {
        GetCallNeededUseCase(callRepository = get())
    }
    factory<GetModelUseCase> {
        GetModelUseCase(get())
    }
    factory<GetImageUseCase> {
        GetImageUseCase(get())
    }
    factory<GetCallHistoryUseCase> {
        GetCallHistoryUseCase(get())
    }
    factory<SaveCallInfoUseCase>{
        SaveCallInfoUseCase(get())
    }
    factory<GetAuthDataUseCase> {
        GetAuthDataUseCase(get())
    }
    factory<SendDataToSharedFlowUseCase> {
        SendDataToSharedFlowUseCase(get())
    }
    factory<SetAuthDataUseCase> {
        SetAuthDataUseCase(get())
    }
}