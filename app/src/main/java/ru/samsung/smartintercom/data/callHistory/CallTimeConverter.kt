package ru.samsung.smartintercom.data.callHistory

import androidx.room.TypeConverter
import ru.samsung.smartintercom.domain.callHistory.models.CallTime

class CallTimeConverter {

    @TypeConverter
    fun fromCallTime(callTime: CallTime): String {
        return "${callTime.minutes},${callTime.hours},${callTime.day},${callTime.month},${callTime.year}"
    }

    @TypeConverter
    fun toCallTime(callTimeString: String): CallTime {
        val parts = callTimeString.split(",")
        return CallTime(
            minutes = parts[0],
            hours = parts[1],
            day = parts[2],
            month = parts[3],
            year = parts[4]
        )
    }
}