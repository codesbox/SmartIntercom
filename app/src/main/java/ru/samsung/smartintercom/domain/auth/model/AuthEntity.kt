package ru.samsung.smartintercom.domain.auth.model

/**
 * Информация о пользователе, необходимой для похода в API
 *
 * @param house номер дома
 * @param room номер комнаты
 */
data class AuthEntity(
    val house: String,
    val room: String,
)
