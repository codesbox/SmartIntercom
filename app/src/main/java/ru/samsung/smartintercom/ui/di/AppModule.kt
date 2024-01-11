package ru.samsung.smartintercom.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.samsung.smartintercom.ui.activity.MainActivityViewModel
import ru.samsung.smartintercom.ui.screen.call.CallViewModel
import ru.samsung.smartintercom.ui.screen.main.MainViewModel

val appModule = module {
    viewModel{ MainActivityViewModel(getCallNeededUseCase = get()) }
    viewModel { CallViewModel() }
    viewModel { MainViewModel() }
}