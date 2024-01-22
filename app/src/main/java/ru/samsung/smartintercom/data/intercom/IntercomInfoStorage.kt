package ru.samsung.smartintercom.data.intercom

import android.content.Context

class IntercomInfoStorage {
    fun getInfo(context: Context): IntercomInfoDTO {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        
        val house = pref.getString("house", "") ?: ""
        val flat = pref.getString("flat", "") ?: ""
        return IntercomInfoDTO(house, flat)
    }
    
    fun setInfo(context: Context, intercomInfoDTO: IntercomInfoDTO) {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        
        pref.edit().putString("house", intercomInfoDTO.house)
            .putString("flat", intercomInfoDTO.flat).apply()
    }
}