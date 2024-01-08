package ru.samsung.smartintercom.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.samsung.smartintercom.data.auth.repo.AuthRepositoryImpl
import ru.samsung.smartintercom.data.call.CallRepositoryImpl
import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.call.CallRepository


@Module
interface AppModule {
    @Binds
    fun authRepo(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
    @Binds
    fun callRepo(callRepositoryImpl: CallRepositoryImpl): CallRepository

    companion object {
        @Provides
        fun provideSharedPrefs(context: Context): SharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
    }
}
