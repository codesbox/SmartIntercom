package ru.samsung.smartintercom.ui.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import ru.samsung.smartintercom.R
import ru.samsung.smartintercom.ui.activity.MainActivity

class NotificationHelper {
    
    fun createNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.smartintercomlogo)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText(context.resources.getString(R.string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
            .build()
        
        val notificationManger = context.getSystemService<NotificationManager>()
        notificationManger?.notify(NOTIFICATION_ID, notification)
    }
    
    fun cancelNotifications(context: Context) {
        val notificationManager = context.getSystemService<NotificationManager>()
        notificationManager?.cancelAll()
    }
    
    companion object {
        const val CHANNEL_ID = "notification_channel"
        
        const val NOTIFICATION_ID = 1
    }
}