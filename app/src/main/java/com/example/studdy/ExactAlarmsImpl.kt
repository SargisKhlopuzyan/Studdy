package com.example.studdy

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.ExactAlarms
import com.example.studdy.constants.EXACT_ALARM_INTENT_REQUEST_CODE
import com.example.studdy.receivers.ExactAlarmBroadcastReceiver

class ExactAlarmsImpl(
    private val context: Context
) : ExactAlarms {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

    override fun clearExactAlarm() {

    }

    override fun setExactAlarmSetAlarmClock(triggerAtMillis: Long) {
        // 1
        val pendingIntent = createExactAlarmIntent()
        // 2
        val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerAtMillis, pendingIntent)
        // 3
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
    }

    private fun createExactAlarmIntent(): PendingIntent {
        // 1
        val intent = Intent(context, ExactAlarmBroadcastReceiver::class.java)
        // 2
        return PendingIntent.getBroadcast(
            context,
            EXACT_ALARM_INTENT_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}