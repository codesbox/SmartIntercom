package ru.samsung.smartintercom.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.samsung.smartintercom.ui.activity.MainActivityViewModel
import ru.samsung.smartintercom.ui.screen.call.CallViewModel
import ru.samsung.smartintercom.ui.screen.main.MainViewModel
import ru.samsung.smartintercom.ui.screen.setting.SettingViewModel
import ru.samsung.smartintercom.ui.screen.callhistory.CallHistoryViewModel

val appModule = module {
    viewModelOf(::MainActivityViewModel)
    viewModelOf(::CallViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::CallHistoryViewModel)
}