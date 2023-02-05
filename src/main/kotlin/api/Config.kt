package api

interface Config {
    fun <T> read(config: Class<T>): T
    fun <T> write(config: Class<T>, content: T)
}