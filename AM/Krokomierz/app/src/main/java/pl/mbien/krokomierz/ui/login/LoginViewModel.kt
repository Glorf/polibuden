package pl.mbien.krokomierz.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_login.*
import pl.mbien.krokomierz.data.LoginRepository
import pl.mbien.krokomierz.data.Result

import pl.mbien.krokomierz.R
import pl.mbien.krokomierz.data.model.LoggedInUser

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    class loginAsync(private var activity: LoginActivity) : AsyncTask<String, String, Result<LoggedInUser>>() {
        override fun doInBackground(vararg params: String?): Result<LoggedInUser> {
            return activity.loginViewModel.loginRepository.login(activity.username.text.toString(), activity.password.text.toString())
        }

        override fun onPostExecute(result: Result<LoggedInUser>?) {
            super.onPostExecute(result)

            if (result is Result.Success<LoggedInUser>) {
                activity.loginViewModel._loginResult.value = LoginResult(success = LoggedInUserView(result.data.username, result.data.score, result.data.position, result.data.next))
            } else {
                activity.loginViewModel._loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun login(activity: LoginActivity) {
        loginAsync(activity).execute()
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.isNotBlank()
    }
}
