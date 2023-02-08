package utils.reader

data class Patches(
    val patches: List<Version>
)

data class Version(
    val id: String,
    val version: String,
)
