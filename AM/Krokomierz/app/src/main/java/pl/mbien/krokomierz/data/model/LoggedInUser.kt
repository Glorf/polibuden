package pl.mbien.krokomierz.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val username: String,
    val score: Int,
    val position: Int,
    val next: Int
)
