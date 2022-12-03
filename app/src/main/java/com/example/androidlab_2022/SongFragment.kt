package com.example.androidlab_2022

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidlab_2022.databinding.FragmentFirstBinding
import com.example.androidlab_2022.databinding.FragmentSongBinding
import java.net.URI


//class SongFragment : Fragment(R.layout.fragment_song) {
//    private var binding: FragmentSongBinding? = null
//    private var id_of_song: Int = 0
////    private var musicService: HelloService? = null
////    private var binder: HelloService.HelloBinder? = null
//
//    private val connection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
////            musicService = (service as? HelloService.HelloBinder)?.getService()
////            if(musicService != null) {
//                arguments?.getInt("id").let { id ->
//                    if (id != null) {
//                        setView(id)
//                    }
//                }
//            }
////        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
////            musicService = null
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentSongBinding.bind(view)
//
//        arguments?.getInt("")
//    }
//
////    override fun onResume() {
////        super.onResume()
////        val intent = Intent(
////            this.context,
////            HelloService::class.java
////        )
////        activity?.bindService(
////            intent,
////            connection,
////            Context.BIND_AUTO_CREATE
////        )
////    }
//
//    private fun setView(id: Int) {
//        setTrackView(id)
//        initMusic(id)
//    }
//
//    private fun initMusic(id: Int) {
//        binding?.apply {
//
//            with(binding){
////                this?.ibPlay?.setOnClickListener {
////                    musicService?.play()
////                }
////                this?.ibPause?.setOnClickListener {
////                    musicService?.pause()
////                }
////                this?.ibPrev?.setOnClickListener {
////                    musicService?.prev()
////                    update(musicService?.getId()?:0)
////                }
////                this?.ibNext?.setOnClickListener {
////                    musicService?.next()
////                    update(musicService?.getId()?:0)
////                }
////                this?.ibStop?.setOnClickListener {
////                    musicService?.stop()
////                }
//
//            }
//        }
//    }
//
//    private fun update(id: Int) {
//        setTrackView(id)
//        with(binding) {
//            this?.ibPlay?.setOnClickListener {
////                musicService?.play()
//            }
//        }
//    }
//
//    private fun setTrackView(id: Int) {
//        val cur = SongRep.songs[id]
//
//        with(binding) {
//            this?.tvSong?.text = cur.name
//            this?.tvType?.text = cur.author
//            this?.textView2?.text = cur.genre
//            this?.ivImg?.let {
//                Glide.with(this@SongFragment)
//                    .load(cur.cover)
//                    .into(it)
//            }
//        }
//    }
//    companion object {
//        const val DESCRIPTION_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
//        const val SONG_ID = "SONG_ID"
//        fun newInstance(id_song : Int) =
//            SongFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(SONG_ID, id_song)
//                }
//            }
//    }
//}