package pl.mbien.krokomierz.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val username: String,
    val score: Int,
    val position: Int,
    val next: Int
)