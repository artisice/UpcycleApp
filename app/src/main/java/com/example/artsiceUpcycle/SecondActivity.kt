package com.example.artsiceUpcycle

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import java.util.*

@SuppressLint("UnspecifiedImmutableFlag")
@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {


    private lateinit var tp: TimePicker
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    private var a: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_second)
        tp = findViewById(R.id.tp)
        tp.setIs24HourView(true)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

    }

    fun setAlarm(view: View) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, tp.currentHour)
            calendar.set(Calendar.MINUTE, tp.currentMinute)
            var clock = calendar.timeInMillis - calendar.timeInMillis % 60000
            val intent = Intent(this, AlarmReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (System.currentTimeMillis() > clock) {
                if (Calendar.AM_PM == 0)
                    clock += 1000 * 60 * 60 * 12 - 10000
                else
                    clock += clock + 1000 * 60 * 60 * 24 - 10000
            }
            a = false
            alarmManager.set(AlarmManager.RTC_WAKEUP, clock, pendingIntent)
            Toast.makeText(this, "Alarm is on", Toast.LENGTH_SHORT).show()

    }
    fun offAlarm(view: View){
            android.os.Process.killProcess(android.os.Process.myPid())

    }
}

