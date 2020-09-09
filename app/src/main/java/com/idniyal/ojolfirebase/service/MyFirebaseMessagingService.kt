package com.idniyal.ojolfirebase.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.idniyal.ojolfirebase.MainActivity


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {

        showNotification()
    }

    @SuppressLint("WrongConstant")
    private fun showNotification() {
        val notificationBuilder = NotificationCompat.Builder(this)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pageIntent = PendingIntent.getActivity(this, 0, intent,
            Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}