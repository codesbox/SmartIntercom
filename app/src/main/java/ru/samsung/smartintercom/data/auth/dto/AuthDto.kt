package ru.samsung.smartintercom.data.auth.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("model")
    val model: String
)
