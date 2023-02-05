package model

import java.util.*

data class AllConfig(
    val version: String,
    val account: List<Account>
)

data class Account(
    val uuid: UUID,
    val name: String,
    val type: Type,
    val tokenType: String?
)

enum class Type {
    ONLINE,OFFLINE,THREE
}