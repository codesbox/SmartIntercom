package ru.samsung.smartintercom.ui.di

import org.koin.dsl.module
import ru.samsung.smartintercom.data.auth.repo.AuthRepositoryImpl
import ru.samsung.smartintercom.data.call.CallDataSource
import ru.samsung.smartintercom.data.call.CallRepositoryImpl
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.call.CallRepository

val dataModule = module {
    single<CallDataSource> {
        CallDataSource(gson = get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
    single<CallRepository> {
        CallRepositoryImpl(authRepo = get(), callDataSource = get())
    }
}