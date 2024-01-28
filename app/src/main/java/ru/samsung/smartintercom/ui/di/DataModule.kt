package ru.samsung.smartintercom.ui.di

import org.koin.dsl.module
import ru.samsung.smartintercom.data.auth.dataSource.AuthDataSourceImpl
import ru.samsung.smartintercom.data.auth.repo.AuthRepositoryImpl
import ru.samsung.smartintercom.data.call.CallDataSource
import ru.samsung.smartintercom.data.call.CallRepositoryImpl
import ru.samsung.smartintercom.data.callHistory.CallHistoryDataSourceImpl
import ru.samsung.smartintercom.data.callHistory.CallHistoryRepositoryImpl
import ru.samsung.smartintercom.data.callRespond.CallRespondDataSourceImpl
import ru.samsung.smartintercom.data.callRespond.CallRespondRepositoryImpl
import ru.samsung.smartintercom.data.intercom.IntercomDataSourceImpl
import ru.samsung.smartintercom.data.intercom.IntercomRepositoryImpl
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.call.CallRepository
import ru.samsung.smartintercom.domain.callHistory.CallHistoryDataSource
import ru.samsung.smartintercom.domain.callHistory.CallHistoryRepository
import ru.samsung.smartintercom.domain.callRespond.CallRespondDataSource
import ru.samsung.smartintercom.domain.callRespond.CallRespondRepository
import ru.samsung.smartintercom.domain.intercom.IntercomDataSource
import ru.samsung.smartintercom.domain.intercom.IntercomRepository

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
    single<IntercomDataSource> {
        IntercomDataSourceImpl(get())
    }
    single<IntercomRepository> {
        IntercomRepositoryImpl(get())
    }
    single<CallHistoryRepository> {
        CallHistoryRepositoryImpl(get())
    }
    single<CallHistoryDataSource> {
        CallHistoryDataSourceImpl(get())
    }
    single<CallRespondRepository> {
        CallRespondRepositoryImpl(get())
    }
    single<CallRespondDataSource> {
        CallRespondDataSourceImpl(get())
    }
}