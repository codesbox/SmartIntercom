package ru.samsung.smartintercom

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.samsung.smartintercom.ui.di.appModule
import ru.samsung.smartintercom.ui.di.dataModule
import ru.samsung.smartintercom.ui.di.domainModule
import ru.samsung.smartintercom.ui.notification.NotificationHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        createNotificationChannel()
        
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationHelper.CHANNEL_ID,
                getString(R.string.notification_text),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            
            channel.description = getString(R.string.app_name)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
