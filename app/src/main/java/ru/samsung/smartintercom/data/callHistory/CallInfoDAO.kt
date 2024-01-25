package ru.samsung.smartintercom.data.callHistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CallInfoDAO {
    @Query("SELECT * FROM callinforoom")
    fun getAll(): Flow<List<CallInfoRoom>>

    @Insert
    fun insert(callInfoRoom: CallInfoRoom)

}