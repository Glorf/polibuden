package pl.mbien.krokomierz

import android.content.ContentValues
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_statistics.*
import org.json.JSONObject
import pl.mbien.krokomierz.data.model.LoggedInUser
import pl.mbien.krokomierz.sql.DatabaseContract
import pl.mbien.krokomierz.sql.DatabaseHelper
import java.lang.Exception
import java.net.URL

class Statistics : AppCompatActivity() {

    var logged: Boolean = false
    var score = 0
    var username: String = "dummy"
    var password: String = ""

    fun getWynikFromDb(username: String) : Int {
        val database = DatabaseHelper(this).readableDatabase

        val cursor = database.rawQuery( "SELECT * FROM " + DatabaseContract.Records.TABLE_NAME + " WHERE " +
                DatabaseContract.Records.COLUMN_NAME+ "=?", arrayOf(username))

        if(cursor.count < 1) {
            cursor.close()
            return 0
        }
        else {
            cursor.moveToFirst()
            val ret = cursor.getInt(2)
            cursor.close()
            return ret
        }
    }

    fun saveWynikToDb(wynik: Int, username: String) {
        val oldWynik = getWynikFromDb(username)
        val database = DatabaseHelper(this).writableDatabase

        val values = ContentValues()
        values.put(DatabaseContract.Records.COLUMN_NAME, username)
        values.put(DatabaseContract.Records.COLUMN_SCORE, wynik)

        if(oldWynik == -1)
            database.insert(DatabaseContract.Records.TABLE_NAME, null, values)
        else
            database.update(DatabaseContract.Records.TABLE_NAME, values, DatabaseContract.Records.COLUMN_NAME+"= ?", arrayOf(username))
    }

    class backup(private var context: Statistics): AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {

            val text = URL("http://mbien.pl:2137/save?username="+context.username+"&password="+context.password+"&score="+context.score).readText()
            return "kesz"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            Toast.makeText(
                context,
                "Backup performed successfully",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    class restore(private var context: Statistics): AsyncTask<String, String, LoggedInUser>() {
        override fun doInBackground(vararg params: String?): LoggedInUser {
            val text = URL("http://mbien.pl:2137/load?username="+context.username+"&password="+context.password).readText()
            if(text == "0") throw Exception()

            println(text)
            val json = JSONObject(text)

            return LoggedInUser(context.username, json.getInt("score"), json.getInt("position"), json.getInt("next"))
        }

        override fun onPostExecute(result: LoggedInUser?) {
            super.onPostExecute(result)

            context.scoreView.text = result?.score.toString()
            context.positionView.text = result?.position.toString()
            context.nextView.text = result?.next.toString()

            context.saveWynikToDb(result!!.score, context.username)

            Toast.makeText(
                context,
                "Restore performed successfully",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val loginShared = this.getSharedPreferences("pl.mbien.krokomierz.prefs", 0)
        username = loginShared.getString("username", "offline")
        password = loginShared.getString("password", "")
        if(password != "") logged = true


        if(logged) {
            score = getWynikFromDb(username)
            scoreView.text = score.toString()
            backupButton.isEnabled = true
            restoreButton.isEnabled = true
        }

        backupButton.setOnClickListener {
            backup(this).execute()
        }

        restoreButton.setOnClickListener {
            restore(this).execute()
        }

        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}
