package ru.samsung.smartintercom.data.auth.dataSource

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.samsung.smartintercom.domain.auth.AuthDataSource
import ru.samsung.smartintercom.domain.auth.model.AuthEntity

class AuthDataSourceImpl(private val context: Context): AuthDataSource {

    private val mutableSharedFlow = MutableSharedFlow<AuthEntity?>()

    override fun getFlow(): Flow<AuthEntity?> {
        return mutableSharedFlow
    }

    override suspend fun setAuthData(authEntity: AuthEntity) {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        pref.edit().putString("house", authEntity.house)
            .putString("room", authEntity.room).apply()
        mutableSharedFlow.emit(authEntity)
    }

    override suspend fun sendDataToSharedFlow() {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val house = pref.getString("house", "") ?: ""
        val room = pref.getString("room", "") ?: ""
        if ((house == "") or (room == "")) {
            mutableSharedFlow.emit(null)
        } else {
            mutableSharedFlow.emit(AuthEntity(house, room))
        }
    }

    override fun getAuthData(): AuthEntity {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val house = pref.getString("house", "") ?: ""
        val room = pref.getString("room", "") ?: ""
        return AuthEntity(house, room)
    }

    override suspend fun sendNullToSharedFlow() {
        mutableSharedFlow.emit(null)
    }


}