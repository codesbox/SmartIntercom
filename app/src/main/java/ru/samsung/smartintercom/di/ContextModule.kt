package ru.samsung.smartintercom.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule(val context: Context) {

    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideGson() = Gson()
}
