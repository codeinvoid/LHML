package config

import java.util.*

data class Auth(
    val online: Online,
    val offline: Offline
)

data class Online(
    val uuid: UUID,
    val name: String,
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String,
    val notAfter: Long,
    val isSelected: Boolean
)

data class Offline(
    val uuid: UUID,
    val name: String,
    val isSelected: Boolean
)