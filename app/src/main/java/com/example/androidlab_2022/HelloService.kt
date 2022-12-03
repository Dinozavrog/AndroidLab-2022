package com.example.androidlab_2022

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder

//class HelloService : Service() {
//
//    private var song_id : Int? = null
//    private lateinit var mySongs: ArrayList<Song>
//    private lateinit var notificationService: NotificationService
//    private lateinit var mediaPlayer: MediaPlayer
//    private lateinit var musicBinder: HelloBinder
//
//    inner class HelloBinder : Binder() {
//        fun getService(): HelloService = this@HelloService
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        song_id = 0
//        mySongs = SongRep.songs
//        mediaPlayer = MediaPlayer()
//        musicBinder = HelloBinder()
//        notificationService = NotificationService(this)
//    }
//
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        intent?.getParcelableExtra<MediaActions>("MEDIA_ACTIONS")?.let {
//            when (it) {
//                MediaActions.PLAY -> play()
//                MediaActions.PAUSE -> pause()
//                MediaActions.STOP -> stop()
//                MediaActions.PREV -> prev()
//                MediaActions.NEXT -> next()
//            }
//        }
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release()
//    }
//
//    override fun onBind(intent: Intent): IBinder? = null
//
//    fun play() {
//        mediaPlayer.start()
//        song_id?.let {
//            notificationService.buildNotification(it)
//    } }
//
//
//    fun next() {
//        song_id?.let {
//            song_id = if (it + 1 < mySongs.size) it + 1
//            else 0
//        }
//
//        setSong(song_id ?: 0)
//        play()
//        song_id?.let {
//            notificationService.buildNotification(it)
//        }
//
//    }
//
//    fun prev() {
//        song_id?.let {
//            song_id = if (it == 0) {
//                mySongs.size - 1
//            } else {
//                it - 1
//            }
//            setSong(song_id ?: 0)
//            play()
//        }
//        song_id?.let {
//            notificationService.buildNotification(it)
//        }
//    }
//
//    fun pause() {
//        if (mediaPlayer.isPlaying) mediaPlayer.stop()
//        song_id?.let {
//            notificationService.buildNotification(it)
//        }
//    }
//
//    fun stop() {
//        if (mediaPlayer.isPlaying) stopSelf()
//        song_id?.let {
//            notificationService.buildNotification(it)
//        }
//    }
//
////    private fun playMyMusic(my_song: Uri) {
////        if (mediaPlayer.isPlaying) mediaPlayer.stop()
////        mediaPlayer = MediaPlayer.create(applicationContext, my_song)
////        mediaPlayer.run {
////            start()
////            setOnCompletionListener {
////                stop()
////            }
////        }
////    }
//
//    private fun setSong(id: Int) {
//        with(mediaPlayer) {
//            if(isPlaying) stop()
//        }
//        mediaPlayer = MediaPlayer.create(
//            applicationContext,
//            mySongs[id].audio)
//
//        song_id = id
//        song_id.let {
//            if (it != null) {
//                notificationService.buildNotification(it)
//            }
//        }
//    }
//
//    fun getId(): Int? {
//        return this.song_id
//    }
//
//    fun get_id() : Int? {
//        return this.song_id
//    }
//
//}