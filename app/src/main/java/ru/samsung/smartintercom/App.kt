package ru.samsung.smartintercom

import android.app.Application
import ru.samsung.smartintercom.di.ContextModule
import ru.samsung.smartintercom.di.DaggerAppComponent

class App : Application() {
    val appComponent = DaggerAppComponent.builder()
        .contextModule(ContextModule(this))
        .build()
}
