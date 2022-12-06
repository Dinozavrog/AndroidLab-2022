package com.example.androidlab_2022

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log

class HelloService : Service() {

//    private var song_id : Int = 2
    private var mySongs: ArrayList<Song> = SongRep.songs
//    private lateinit var notificationService: NotificationService
//    private lateinit var mediaPlayer: MediaPlayer
//    private lateinit var musicBinder: HelloBinder
//
//    inner class HelloBinder : Binder() {
//        fun getService(): HelloService = this@HelloService
//        fun playMyMusic(songID: Int) = this@HelloService.playMyMusic(songID)
//        fun play() = this@HelloService.play()
//        fun pause() = this@HelloService.pause()
//        fun stop() = this@HelloService.stop()
//        fun next() = this@HelloService.next()
//        fun prev() = this@HelloService.next()
//        fun isPlaying(): Boolean = this@HelloService.isPlaying()
//        fun playCurrentMusic(id: Int, progress: Int) = this@HelloService.playCurrentMusic(id, progress)
//        fun getCurrentSong() = this@HelloService.getCurrentSong()
//    }
//
//    private fun playCurrentMusic(id: Int, progress: Int) {
//        playMyMusic(id)
//        playCurrentFromPosition(progress)
//    }
//
//    private fun playCurrentFromPosition(progress: Int) = mediaPlayer.seekTo(progress)
//
////    override fun onCreate() {
////        super.onCreate()
////        song_id = 0
////        mySongs = SongRep.songs
////        mediaPlayer = MediaPlayer()
////        musicBinder = HelloBinder()
////        notificationService = NotificationService(this)
////    }
//
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.e("INYENT", intent.toString())
//        intent?.getParcelableExtra<MediaActions>("MEDIA_ACTIONS")?.let {
//            Log.e("ACTION", it.toString())
//            when (it) {
//                MediaActions.PLAY -> playMyMusic(intent.getIntExtra("RAW", -1))
//                MediaActions.PAUSE -> pause()
//                MediaActions.STOP -> stop()
//                MediaActions.PREV -> prev()
//                MediaActions.NEXT -> next()
//            }
//        }
////        return super.onStartCommand(intent, flags, startId)
//        return START_STICKY
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release()
//    }
//
//    override fun onBind(intent: Intent): IBinder = HelloBinder()
//
//    fun play() {
//        mediaPlayer.start()
//        song_id.let {
//            notificationService.buildNotification(it)
//    } }
//
//
//    fun next() {
//        song_id.let {
//            song_id = if (it + 1 < mySongs.size) it + 1
//            else 0
//        }
//
//        setSong(song_id ?: 0)
//        play()
//        song_id.let {
//            notificationService.buildNotification(it)
//        }
//
//    }
//
//    fun prev() {
//        song_id.let {
//            song_id = if (it == 0) {
//                mySongs.size - 1
//            } else {
//                it - 1
//            }
//            setSong(song_id ?: 0)
//            play()
//        }
//        song_id.let {
//            notificationService.buildNotification(it)
//        }
//    }
//
//    fun pause() {
//        if (mediaPlayer.isPlaying) mediaPlayer.pause()
//        song_id.let {
//            notificationService.buildNotification(it)
//        }
//    }
//
//    fun stop() {
//        if (mediaPlayer.isPlaying) stop()
//        song_id.let {
//            notificationService.buildNotification(it)
//        }
//    }
//
//    private fun playMyMusic(songId: Int) {
//        Log.e("ID", songId.toString())
//        if (mediaPlayer.isPlaying) mediaPlayer.stop()
//        mediaPlayer = MediaPlayer.create(applicationContext, mySongs[songId].audio)
//        mediaPlayer.run {
//            start()
//            setOnCompletionListener {
////                next()
//                stop()
//            }
//        }
//        notificationService.buildNotification(songId)
//    }

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
//    private fun getCurrentSong(): Song? {
//        return if (song_id in 0..mySongs.size)
//            mySongs[song_id]
//        else
//            null
//    }
//
//    private fun isPlaying(): Boolean = mediaPlayer.isPlaying
//
//    fun getId(): Int? {
//        return this.song_id
//    }


    private var mediaPlayer = MediaPlayer()

    inner class HelloBinder : Binder() {

        fun playMusic(raw: Int) {
            play(raw)
        }

        fun pauseMusic() {
            pause()
        }

        fun stopMusic() {
            stop()
        }

        fun nextMusic(raw: Int) {
            next(raw)
        }

        fun previousMusic(raw: Int) {
            previous(raw)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.getParcelableExtra<MediaActions>("MEDIA_ACTION")?.let {
                when (it) {
                MediaActions.PLAY -> play(intent.getIntExtra("RAW", 15))
                MediaActions.PAUSE -> pause()
                MediaActions.STOP -> stop()
                MediaActions.PREV -> previous(0)
                MediaActions.NEXT -> next(0)

            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder = HelloBinder()

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun play(id: Int) {
        Log.e("SongService", "play")
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, mySongs[id].audio)
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop()
            }
        }
    }

    private fun pause() {
        mediaPlayer.pause()
    }

    private fun stop() {
        Log.e("SongService", "stop")
        mediaPlayer.stop()
    }

    private fun previous(raw: Int) {
        play(raw)
    }

    private fun next(raw: Int) {
        play(raw)
    }

}