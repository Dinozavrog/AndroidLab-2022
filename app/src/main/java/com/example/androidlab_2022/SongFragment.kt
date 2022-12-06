package com.example.androidlab_2022

import android.app.Notification.EXTRA_PROGRESS
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.example.androidlab_2022.databinding.FragmentFirstBinding
import com.example.androidlab_2022.databinding.FragmentSongBinding
import java.net.URI
import java.security.Provider


class SongFragment : Fragment(R.layout.fragment_song) {
    private var binding: FragmentSongBinding? = null
    private var id_of_song: Int = 0
    private var musicService: HelloService? = null
    private var binder: HelloService.HelloBinder? = null
    private var c: Int = 5
    private var seekBar: SeekBar? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e("bin", "======")
            binder = service as? HelloService.HelloBinder
            Log.e("binFromServ", binder.toString())
//            arguments?.getInt("id")?.let {
//                val progress = arguments?.getInt(EXTRA_PROGRESS)
//                if (progress != null) {
//                    binder?.playCurrentMusic(it, progress)
//                } else {
//                    binder?.playMyMusic(it)
//                }
//            }
        }


        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        activity?.bindService(
//            Intent(requireContext(), HelloService::class.java),
//            connection,
//            BIND_AUTO_CREATE
//        )
        seekBar = binding?.seekBar

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongBinding.bind(view)
//        pauseOrPlayTrack()
//        binder = service as? HelloService.HelloBinder
        binding?.run {
            val songs = SongRep.songs
            var song_name: String = ""
            var song_author: String = ""
            var song_des: String = ""
            var song_img: String = ""
            c = arguments?.getInt(SONG_ID)?:-100
            for (song in songs) {
                if (song.id == c) {
                    song_name = song.name
                    song_author = song.author
                    song_des = song.genre
                    song_img = song.cover
                    id_of_song = song.id
                }
            }
            tvSong.setText(song_name)
            textView2.setText(song_author)
            tvType.setText(song_des)
            Glide.with(this@SongFragment)
                .load(song_img)
                .into(ivImg)
//            ibPrev.setOnClickListener {
//                playPreviousSong()
//                Log.e("HelloService", "hi")
//            }
//            ibNext.setOnClickListener {
//                playNextTrack()
//            }
//            ibStop.setOnClickListener {
//                stopPlayingSong()
//            }
//            ibPause.setOnClickListener {
//                pauseOrPlayTrack()
//            }
            ibPlay.setOnClickListener {
                pauseOrPlayTrack()
            }
        }

        arguments?.getInt("")


    }


//    private fun setView(id: Int) {
//        initMusic(id)
//    }

//    private fun initMusic(id: Int) {
//        binding?.apply {
//
//            with(binding){
//                this?.ibPlay?.setOnClickListener {
//                    musicService?.play()
//                }
//                this?.ibPause?.setOnClickListener {
//                    musicService?.pause()
//                }
//                this?.ibPrev?.setOnClickListener {
//                    musicService?.prev()
//                    update(musicService?.getId()?:0)
//                }
//                this?.ibNext?.setOnClickListener {
//                    musicService?.next()
//                    update(musicService?.getId()?:0)
//                }
//                this?.ibStop?.setOnClickListener {
//                    musicService?.stop()
//                }
//
//            }
//        }
//    }

//    private fun update(id: Int) {
//        with(binding) {
//            this?.ibPlay?.setOnClickListener {
//                musicService?.playM
//            }
//        }
//    }

    private fun seekBar(id: Int, int: Int) {
        with(binding) {
            this?.seekBar?.progress = 0
            this?.seekBar?.max = id.let { SongRep.findSongById(it) }?.time ?: -1
        }
    }

    private fun playNextTrack() {
//        binder?.next()
//        binder?.getCurrentSong()?.id?.let {
//        }

    }

    private fun playPreviousSong() {
//        binder?.next()
//        binder?.getCurrentSong()?.id?.let {
//        }

    }

    private fun pauseOrPlayTrack() {
//        if (binder?.isPlaying() == true) {
//            requireContext().bindService(Intent(requireContext(), HelloService::class.java).apply {
//                putExtra("MEDIA_ACTION", MediaActions.PAUSE as Parcelable)
//                putExtra("RAW", id_of_song)
//            },
//            connection,
//            Service.BIND_AUTO_CREATE)
//            binder?.pauseMusic()
//            binding?.ibPlay?.setImageResource(R.drawable.ic_play)
//        } else {
            requireContext().bindService(Intent(requireContext(), HelloService::class.java).apply {
                putExtra("MEDIA_ACTION", MediaActions.PLAY as Parcelable)
                putExtra("RAW", id_of_song)
            },
                connection,
                Service.BIND_AUTO_CREATE)
            Log.e("binder", binder.toString())
            binder?.playMusic(id_of_song)
            binding?.ibPlay?.setImageResource(R.drawable.ic_baseline_stop_24)
        }
//    }

    private fun stopPlayingSong() {
        binder?.stopMusic()
        binding?.ibPlay?.setImageResource(R.drawable.ic_play)
    }



    companion object {
        const val DESCRIPTION_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        const val SONG_ID = "SONG_ID"
        fun newInstance(id_song : Int) =
            SongFragment().apply {
                arguments = Bundle().apply {
                    putInt(SONG_ID, id_song)
                }
            }
    }
}