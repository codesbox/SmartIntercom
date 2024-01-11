package ru.samsung.smartintercom.ui.di

import com.google.gson.Gson
import org.koin.dsl.module
import ru.samsung.smartintercom.domain.call.GetCallNeededUseCase

val domainModule = module {
    single<Gson>{
        Gson()
    }
    factory<GetCallNeededUseCase> {
        GetCallNeededUseCase(callRepository = get())
    }
}