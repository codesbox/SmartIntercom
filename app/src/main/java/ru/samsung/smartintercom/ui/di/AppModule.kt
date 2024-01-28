package ru.samsung.smartintercom.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.samsung.smartintercom.ui.activity.MainActivityViewModel
import ru.samsung.smartintercom.ui.screen.call.CallViewModel
import ru.samsung.smartintercom.ui.screen.main.MainViewModel
import ru.samsung.smartintercom.ui.screen.setting.SettingViewModel
import ru.samsung.smartintercom.ui.screen.callhistory.CallHistoryViewModel
import ru.samsung.smartintercom.ui.notification.NotificationHelper

val appModule = module {
    viewModelOf(::MainActivityViewModel)
    viewModelOf(::CallViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::CallHistoryViewModel)
    
    singleOf(::NotificationHelper)
}