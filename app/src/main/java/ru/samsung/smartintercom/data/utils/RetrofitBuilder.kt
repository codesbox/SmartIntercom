package ru.samsung.smartintercom.data.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.samsung.smartintercom.core.CoreConstants

object RetrofitBuilder {

    val retrofit = Retrofit.Builder().baseUrl(CoreConstants.HOST).addConverterFactory(
        GsonConverterFactory.create())
        .build()

}