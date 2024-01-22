package ru.samsung.smartintercom.data.intercom

import android.content.Context

class IntercomInfoRepositoryImpl(private val intercomInfoStorage: IntercomInfoStorage) {
    fun getInfo(context: Context): IntercomInfoDTO{
        return intercomInfoStorage.getInfo(context)
    }
    
    fun saveInfo(context: Context, intercomInfoDTO: IntercomInfoDTO){
        intercomInfoStorage.setInfo(context, intercomInfoDTO)
    }
}