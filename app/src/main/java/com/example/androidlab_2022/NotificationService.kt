package com.example.androidlab_2022

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat


//const val PREV = "PREVIOUS"
//const val PAUSE = "PAUSE"
//const val PLAY = "PLAY"
//const val STOP = "STOP"
//const val NEXT = "NEXT"
//private const val CHANNEL_ID = "track_channel"
//
//class NotificationService (
//    val context: Context) {
//    private var prevPendingIntent: PendingIntent? = null
//    private var pausePendingIntent: PendingIntent? = null
//    private var nextPendingIntent: PendingIntent? = null
//    private var stopPendingIntent: PendingIntent? = null
//    private var playPendingIntent: PendingIntent? = null
//    private var notificationPendingIntent: PendingIntent? = null
//
//    private val manager by lazy {
//        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//    }
//
//    init{
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel(
//                CHANNEL_ID,
//                context.getString(R.string.channel_name),
//                IMPORTANCE_DEFAULT
//            ).apply {
//                description = context.getString(R.string.channel_description)
//            }.also {
//                manager.createNotificationChannel(it)
//            }

//            val previousIntent = Intent(context,HelloService::class.java).apply {
//                action = PREV
//            }
//            val pauseIntent = Intent(context,HelloService::class.java).apply {
//                action = PAUSE
//            }
//            val nextIntent = Intent(context,HelloService::class.java).apply {
//                action = NEXT
//            }
//            val stopIntent = Intent(context, HelloService::class.java).apply {
//                action = STOP
//            }
//            val playIntent = Intent(context,  HelloService::class.java).apply{
//                action = PLAY
//            }
//            val notificationIntent = Intent(context, MainActivity::class.java)
//            prevPendingIntent = PendingIntent.getService(
//                context,
//                0,
//                previousIntent,
//                0
//            )
//            pausePendingIntent = PendingIntent.getService(
//                context,
//                1,
//                pauseIntent,
//                0
//            )
//            nextPendingIntent = PendingIntent.getService(
//                context,
//                2,
//                nextIntent,
//                0
//            )
//            stopPendingIntent = PendingIntent.getService(
//                context,
//                3,
//                stopIntent,
//                0
//            )
//            playPendingIntent = PendingIntent.getService(
//                context,
//                4,
//                playIntent,
//                0
//            )
//            notificationPendingIntent = PendingIntent.getActivity(
//                context,
//                5,
//                notificationIntent,
////                PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//    }
//
//    fun buildNotification(id:Int){
//
//        val track = SongRep.songs[id]
//
//        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_play)
//            .addAction(R.drawable.ic_prev,"Previous", prevPendingIntent)
//            .addAction(R.drawable.ic_baseline_pause_24,"Pause", pausePendingIntent)
//            .addAction(R.drawable.ic_play, "Play", playPendingIntent)
//            .addAction(R.drawable.ic_next,"Next", nextPendingIntent)
//            .addAction(R.drawable.ic_baseline_stop_24, "Stop", stopPendingIntent)
//            .setContentTitle(track.name)
//            .setContentText(track.autor)
//            .setLargeIcon(BitmapFactory.decodeResource(context.resources,track.audio))
//            .setContentIntent(notificationPendingIntent)
//
//        manager.notify(id, builder.build())
//    }
//
//    fun rebuildNotification(id:Int){
//        val track = SongRep.songs[id]
//
//        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_play)
//            .addAction(R.drawable.ic_prev,"Previous", prevPendingIntent)
//            .addAction(R.drawable.ic_play, "Play", playPendingIntent)
//            .addAction(R.drawable.ic_baseline_pause_24,"Pause", pausePendingIntent)
//            .addAction(R.drawable.ic_next,"Next", nextPendingIntent)
//            .addAction(R.drawable.ic_baseline_stop_24, "Stop", stopPendingIntent)
//            .setContentTitle(track.name)
//            .setContentText(track.autor)
//            .setLargeIcon(BitmapFactory.decodeResource(context.resources,track.audio))
//            .setContentIntent(notificationPendingIntent)
//
//        manager.notify(id, builder.build())
//    }
//}
