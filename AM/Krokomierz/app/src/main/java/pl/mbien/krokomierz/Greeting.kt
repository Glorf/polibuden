package pl.mbien.krokomierz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Greeting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        val thread = Thread {
            run {
                Thread.sleep(3000)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        thread.start()
    }
}
