package com.example.artsiceUpcycle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL

@Suppress("DEPRECATION")
class WeatherAppActivity : AppCompatActivity() {

    private var StringInfo: EditText? = null
    private var button_info: Button? = null
    private var result: TextView? = null
    @Suppress("BlockingMethodInNonBlockingContext")
    @DelicateCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_weather_app)

        StringInfo = findViewById(R.id.StringInfo)
        button_info = findViewById(R.id.button_info)
        result = findViewById(R.id.result)

        button_info?.setOnClickListener {
            if(StringInfo?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Введите город", Toast.LENGTH_LONG).show()
            else {
                val city: String = StringInfo?.text.toString()
                val key = "2f5553146a9b9112f53781eb4c5743c5"
                val url = "https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=$key&units=metric&lang=ru"


                GlobalScope.launch {
                    val dispatcher = this.coroutineContext
                    CoroutineScope(dispatcher).launch {
                        val apiResponse = URL(url).readText()

                        val weather = JSONObject(apiResponse).getJSONArray("weather")
                        val desc = weather.getJSONObject(0).getString("description")

                        val main = JSONObject(apiResponse).getJSONObject("main")
                        val temp = main.getString("temp")

                        this@WeatherAppActivity.runOnUiThread {
                            result?.text = "Температура: $temp°C\n\n$desc"
                        }

                    }
                }


            }
        }
    }


}