package com.example.myapplication.data.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.PetProject.Companion.CHANNEL_ID
import com.example.myapplication.R
import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.feature.commentlist.ui.list.dialog.ImageSourceDialogFragment.Companion.IMAGE_URI_EXTRA
import dagger.android.AndroidInjection
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class FireStorageService : Service() {
    companion object {
        const val FIRE_STORAGE_NOTIFICATION_ID = 1

        fun callingIntent(context: Context): Intent {
            return Intent(context, FireStorageService::class.java)
        }
    }

    @Inject
    lateinit var userRepository: UserRepository

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private val PROGRESS_MAX = 100
    private val PROGRESS_CURRENT = 0

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Picture upload")
            setContentText("Upload in progress")
            setSmallIcon(R.drawable.favourite_star)
            setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            priority = NotificationCompat.PRIORITY_LOW
        }

        NotificationManagerCompat.from(this).apply {
            // Issue the initial notification with zero progress
            serviceScope.launch {
                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
                notify(FIRE_STORAGE_NOTIFICATION_ID, builder.build())

                intent?.let {
                    it.getStringExtra(IMAGE_URI_EXTRA)?.let { uri ->
                        userRepository.uploadProfilePicture(uri).also { user ->
                            EventBus.getDefault().post(user)
                        }
                    }
                }

                builder.setContentText("Upload complete")
                    .setProgress(0, 0, false)
                notify(FIRE_STORAGE_NOTIFICATION_ID, builder.build())
            }
        }

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}
