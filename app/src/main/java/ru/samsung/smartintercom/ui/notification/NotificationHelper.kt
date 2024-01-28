package ru.samsung.smartintercom.ui.notification

import android.app.Notification
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
        
        val intent = Intent(context, MainActivity::class.java)
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
        println(notificationManger == null)
        notificationManger?.notify(NOTIFICATION_ID, notification)
    }
    
    fun notification(context: Context) :Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.smartintercomlogo)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText(context.resources.getString(R.string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
            .build()
    }
    
    
    fun cancelNotification(context: Context) {
        val notificationManager = context.getSystemService<NotificationManager>()
        notificationManager?.cancelAll()
    }
    
    companion object {
        const val CHANNEL_ID = "notification_channel"
        
        const val NOTIFICATION_ID = 1
    }
}