package com.example.myapplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.myapplication.core.constants.CHANNEL_ID
import com.example.myapplication.di.components.CoreComponent
import com.example.myapplication.di.components.DaggerAppComponent
import com.example.myapplication.di.components.DaggerCoreComponent
import com.example.myapplication.di.modules.ContextModule

class PetProject : Application() {

    lateinit var coreComponent: CoreComponent

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as PetProject).coreComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDaggerCoreComponent()
        initDaggerAppComponent()
        initNotificationChannel()
    }

    private fun initDaggerCoreComponent() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    private fun initDaggerAppComponent() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "TEST"
            val descriptionText = "TEST"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
