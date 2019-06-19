package pl.mbien.krokomierz

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorEvent

import kotlinx.android.synthetic.main.activity_main.*
import pl.mbien.krokomierz.ui.login.LoginActivity
import android.content.ContentValues
import pl.mbien.krokomierz.sql.DatabaseContract
import pl.mbien.krokomierz.sql.DatabaseHelper
import kotlin.math.max


class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var stepCounter: Sensor
    var delta = 0
    var wynik = 0
    var savedWynik = 0
    var logged = false
    var firstCheck = true
    var username = "dummy"
    var remoteCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logowanieButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        statystykiButton.setOnClickListener {
            finish()
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
        }

        getUserFromPreferences()
        if(logged) {
            wynik = getWynikFromDb(username)
            savedWynik = wynik
            remoteCount = wynik
            stepsView.text = wynik.toString()
            logowanieButton.setOnClickListener { logout() }
            logowanieButton.text = "Wyloguj"
        }

        loginStatus.text = username

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun getWynikFromDb(username: String) : Int {
        val database = DatabaseHelper(this).readableDatabase

        val cursor = database.rawQuery( "SELECT * FROM " + DatabaseContract.Records.TABLE_NAME + " WHERE " +
                DatabaseContract.Records.COLUMN_NAME+ "=?", arrayOf(username))

        if(cursor.count < 1) {
            cursor.close()
            database.close()
            return -1
        }
        else {
            cursor.moveToFirst()
            val ret = cursor.getInt(2)
            cursor.close()
            database.close()
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


    fun getUserFromPreferences() {
        val loginShared = this.getSharedPreferences("pl.mbien.krokomierz.prefs", 0)
        username = loginShared.getString("username", "offline")
        if(loginShared.getString("password", "") != "") logged = true
    }

    fun logout() {
        val loginShared = this.getSharedPreferences("pl.mbien.krokomierz.prefs", 0)
        val editor = loginShared.edit()
        editor.putString("username", "offline")
        editor.putString("password", "")
        editor.apply()

        intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if(firstCheck) {
            delta = event.values[0].toInt()
            firstCheck = false
        } else {
            wynik = event.values[0].toInt() - delta + remoteCount
            stepsView.text = wynik.toString()
        }

        if(wynik  - savedWynik > 50) {
            saveWynikToDb(wynik, username)
            savedWynik = wynik
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        sensorManager.unregisterListener(this)
    }
}
