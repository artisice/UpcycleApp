package com.example.artsiceUpcycle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast


class AlarmReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context?, intent: Intent?) {

        var alarmUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        val uri = RingtoneManager.getRingtone(context, alarmUri)
        uri!!.play()
        Toast.makeText(context, "Time to wake up!", Toast.LENGTH_LONG).show()
    }
}