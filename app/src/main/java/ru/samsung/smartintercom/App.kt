package ru.samsung.smartintercom

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.samsung.smartintercom.ui.di.appModule
import ru.samsung.smartintercom.ui.di.dataModule
import ru.samsung.smartintercom.ui.di.domainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}
