package utils.reader

data class Skin(
    val properties: List<Item>
)

data class Item(
    val name: String,
    val value: String
)