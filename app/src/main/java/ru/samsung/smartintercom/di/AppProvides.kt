package ru.samsung.smartintercom.di

import ru.samsung.smartintercom.domain.auth.AuthRepository
import ru.samsung.smartintercom.domain.call.CallRepository

interface AppProvides {
    fun authRepo(): AuthRepository
    fun callRepo(): CallRepository
}
