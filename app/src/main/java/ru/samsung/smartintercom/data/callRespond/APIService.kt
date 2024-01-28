package ru.samsung.smartintercom.data.callRespond


import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import ru.samsung.smartintercom.core.CoreConstants

interface APIService {
    @POST("call")
    suspend fun sendStatus(@Header(CoreConstants.HEADER_HOUSE) house: String,
                   @Header(CoreConstants.HEADER_FLAT) flat: String,
                   @Body callRespondRequestBody: CallRespondRequestBody): String
}