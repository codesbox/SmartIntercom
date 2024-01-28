package ru.samsung.smartintercom.data.utils

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.samsung.smartintercom.core.CoreConstants


object RetrofitBuilder {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder().baseUrl(CoreConstants.HOST).addConverterFactory(
        GsonConverterFactory.create(gson))
        .build()

}