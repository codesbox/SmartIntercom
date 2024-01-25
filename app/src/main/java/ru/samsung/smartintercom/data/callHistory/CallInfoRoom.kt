package ru.samsung.smartintercom.data.callHistory

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.samsung.smartintercom.domain.callHistory.models.CallTime
import ru.samsung.smartintercom.domain.callHistory.models.DoorState
@Entity
data class CallInfoRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val callTime: CallTime,
    val doorState: DoorState
)
