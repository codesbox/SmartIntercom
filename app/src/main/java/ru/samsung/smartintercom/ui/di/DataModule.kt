package ru.samsung.smartintercom.ui.di

import org.koin.dsl.module
import ru.samsung.smartintercom.data.auth.dataSource.AuthDataSourceImpl
import ru.samsung.smartintercom.data.auth.repo.AuthRepositoryImpl
import ru.samsung.smartintercom.data.call.CallDataSource
import ru.samsung.smartintercom.data.call.CallRepositoryImpl
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.call.CallRepository

val dataModule = module {
    single<CallDataSource> {
        CallDataSource(gson = get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single<CallRepository> {
        CallRepositoryImpl(authRepo = get(), callDataSource = get())
    }
    single<AuthDataSource> {
        AuthDataSourceImpl(get())
    }
}