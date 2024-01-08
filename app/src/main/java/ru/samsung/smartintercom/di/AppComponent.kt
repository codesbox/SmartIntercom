package ru.samsung.smartintercom.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        ContextModule::class,
    ]
)
@Singleton
interface AppComponent : AppProvides
