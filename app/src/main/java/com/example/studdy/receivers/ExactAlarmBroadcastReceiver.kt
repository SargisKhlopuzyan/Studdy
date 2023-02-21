package com.example.studdy.receivers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.studdy.R
import com.example.studdy.StuddyApplication
import com.example.studdy.StudyActivity
import com.example.studdy.constants.NOTIFICATION_CHANNEL_ID
import com.example.studdy.constants.NOTIFICATION_CHANNEL_NAME
import com.example.studdy.constants.NOTIFICATION_ID

class ExactAlarmBroadcastReceiver : BroadcastReceiver() {
    // 1
    override fun onReceive(context: Context, intent: Intent) {
        Log.e("LOG_TAG", "ExactAlarmBroadcastReceiver -> onReceive")
        // 2
        showNotification(
            context,
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NOTIFICATION_ID,
            "Time to study!"
        )
//        (context.applicationContext as StuddyApplication).apply {
//            exactAlarms.clearExactAlarm()
//            alarmRingtoneState.value = playRingtone(context)
//        }
    }

    private fun playRingtone(context: Context): String {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).toString()
    }

    private fun showNotification(
        context: Context,
        channelId: String,
        notificationChannelName: String,
        notificationId: Int,
        s: String
    ) {
        val channel = NotificationChannel(
            channelId,
            notificationChannelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = s
        }

        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        channel.setSound(
            soundUri,
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build()
        )
        channel.enableVibration(true)

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(notificationId, createNotification(context, channelId))
    }

    private fun createNotification(context: Context, channelId: String): Notification {
//        val channelId = "all_notifications" // Use same Channel ID
        val intent = Intent(context, StudyActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        val builder =
            NotificationCompat.Builder(context, channelId) // Create notification with channel Id
                .setSmallIcon(R.drawable.ic_alarm_vector)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_MAX)
        builder.setContentIntent(pendingIntent).setAutoCancel(true)
        return builder.build()
    }

}