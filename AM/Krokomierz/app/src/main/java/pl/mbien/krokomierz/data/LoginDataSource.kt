package pl.mbien.krokomierz.data

import org.json.JSONObject
import pl.mbien.krokomierz.data.model.LoggedInUser
import java.io.IOException
import java.lang.Exception
import java.net.URL

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val text = URL("http://mbien.pl:2137/load?username="+username+"&password="+password).readText()
            if(text == "0") throw Exception()

            println(text)
            val json = JSONObject(text)
            val user = LoggedInUser(
                username,
                json.getInt("score"),
                json.getInt("position"),
                json.getInt("next"))
            return Result.Success(user)
        } catch (e: Throwable) {
            System.out.println(e)
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

