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
    private var id_of_song: Int = -1
    private var musicService: HelloService? = null
    private var binder: HelloService.HelloBinder? = null
    private var c: Int = 5
    private var seekBar: SeekBar? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as? HelloService.HelloBinder
            arguments?.getInt("SONG_ID")?.let {
                val progress = arguments?.getInt(EXTRA_PROGRESS)
                if (progress != null) {
                    binder?.playCurrentMusic(it, progress)
                } else {
                    binder?.playMyMusic(it)
                }
                addView(it)
            }
        }


        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.bindService(
            Intent(requireContext(), HelloService::class.java),
            connection,
            BIND_AUTO_CREATE
        )
        seekBar = binding?.seekBar

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongBinding.bind(view)
//        pauseOrPlayTrack()
        binding?.run {
            ibPrev.setOnClickListener {
                playPreviousSong()
                Log.e("HelloService", "hi")
            }
            ibNext.setOnClickListener {
                playNextTrack()
            }
            ibPlay.setOnClickListener {
                pauseOrPlayTrack()
            }
        }

        arguments?.getInt("")


    }

    private fun addView(id: Int) {
        val song = id.let { SongRep.findSongById(it) }
        binding?.run {
            song?.let { sing  ->
                Glide.with(this@SongFragment)
                    .load(sing.cover)
                    .into(ivImg)
                tvSong.setText(sing.name)
                textView2.setText(sing.author)
                tvType.setText(sing.genre)
            }}


    }

    private fun update(id: Int) {
        with(binding) {
            this?.ibPlay?.setOnClickListener {
                musicService?.play()
            }
        }
    }

    private fun seekBar(id: Int, int: Int) {
        with(binding) {
            this?.seekBar?.progress = 0
            this?.seekBar?.max = id.let { SongRep.findSongById(it) }?.time ?: -1
        }
    }

    private fun playNextTrack() {
        binder?.next()
        binder?.getCurrentSong()?.id?.let {
            addView(it)
        }


    }

    private fun playPreviousSong() {
        binder?.next()
        binder?.getCurrentSong()?.id?.let {
            addView(it)
        }

    }

    private fun pauseOrPlayTrack() {
        if (binder?.isPlaying() == true) {
            binder?.pause()
            binding?.ibPlay?.setImageResource(R.drawable.ic_play)
        } else {
            binder?.play()
            binding?.ibPlay?.setImageResource(R.drawable.ic_baseline_pause_24)
        }
    }

    private fun stopPlayingSong() {
        binder?.stop()
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