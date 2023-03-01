package com.example.androidlab_2022

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val i = Intent(context, SecondActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val builder = NotificationCompat.Builder(context!!, "dinozavr")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("DINOZAVR")
            .setContentText("DINODINO")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notifictaionManager = NotificationManagerCompat.from(context)
        notifictaionManager.notify(123, builder.build())
    }
}