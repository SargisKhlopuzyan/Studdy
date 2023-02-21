package com.example.studdy.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmPermissionStateChangedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarm Permission State Changed Receiver", Toast.LENGTH_LONG).show()
        Log.e("LOG_TAG", "Alarm Permission State Changed Receiver")
    }

}