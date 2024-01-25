package ru.samsung.smartintercom.data.intercom

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Streaming
import ru.samsung.smartintercom.core.CoreConstants
import ru.samsung.smartintercom.domain.intercom.IntercomModel

interface APIService{
    @GET("info")
    suspend fun getModel(
        @Header(CoreConstants.HEADER_HOUSE) house: String,
        @Header(CoreConstants.HEADER_FLAT) flat: String
    ): IntercomModel
    @GET("image")
    @Streaming
    suspend fun getImage(
        @Header(CoreConstants.HEADER_HOUSE) house: String,
        @Header(CoreConstants.HEADER_FLAT) flat: String
    ): Response<ResponseBody>
}
