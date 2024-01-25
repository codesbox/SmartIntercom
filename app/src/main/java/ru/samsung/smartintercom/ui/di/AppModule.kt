package ru.samsung.smartintercom.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.samsung.smartintercom.ui.activity.MainActivityViewModel
import ru.samsung.smartintercom.ui.screen.call.CallViewModel
import ru.samsung.smartintercom.ui.screen.main.MainViewModel
import ru.samsung.smartintercom.ui.screen.setting.SettingViewModel

val appModule = module {
    viewModel{ MainActivityViewModel(getCallNeededUseCase = get(), authDataSource = get()) }
    viewModel { CallViewModel() }
    viewModel { MainViewModel(get(), get()) }

    viewModelOf(::SettingViewModel)
}